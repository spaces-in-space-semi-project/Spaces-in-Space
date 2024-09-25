package com.space.spacesinspace.admin.product.controller;

import com.space.spacesinspace.admin.product.model.service.ProductAdminService;
import com.space.spacesinspace.common.dto.ProductDTO;
import com.space.spacesinspace.common.dto.CategoryDTO;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("admin/product/*")
public class ProductAdminController {

    private final ResourceLoader resourceLoader;
    private final ProductAdminService productAdminService;

    @Autowired
    public ProductAdminController(ResourceLoader resourceLoader, ProductAdminService productAdminService) {
        this.resourceLoader = resourceLoader;
        this.productAdminService = productAdminService;
    }

    @GetMapping("/productsManage")    // 전체 조회
    public ModelAndView findAllProduct(ModelAndView mv) {
        List<ProductDTO> productList = productAdminService.findAllProduct();
        mv.addObject("productList", productList);
        mv.addObject("activeSection", "product");
        mv.setViewName("admin/layout/adminLayout");
        return mv;
    }

    @GetMapping(value = "findCategory", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {
        return productAdminService.findAllCategory();
    }

    @GetMapping("productRegist") // 등록 페이지
    public ModelAndView registProduct(ModelAndView mv) {
        mv.addObject("activeSection", "productRegist");
        mv.setViewName("admin/layout/adminLayout");
        return mv;
    }

    @PostMapping("/productRegist")
    public ModelAndView registProduct(
            @RequestParam("categoryCode") int categoryCode,
            @RequestParam("productName") String productName,
            @RequestParam("productPrice") int productPrice,
            @RequestParam("productDeliverTime") String productDeliverTime,
            @RequestParam("productDelieverCost") int productDelieverCost,
            @RequestParam("productSize") String productSize,
            @RequestParam("productMaterial") String productMaterial,
            @RequestParam("productDescription") String productDescription,
            @RequestParam("productImageOriginal") MultipartFile productImageOriginal,
            RedirectAttributes redirectAttributes,
            ModelAndView mv) throws IOException {
        ProductDTO newProduct = new ProductDTO();
        newProduct.setCategoryCode(categoryCode);
        newProduct.setProductName(productName);
        newProduct.setProductPrice(productPrice);
        newProduct.setProductDeliverTime(productDeliverTime);
        newProduct.setProductDelieverCost(productDelieverCost);
        newProduct.setProductSize(productSize);
        newProduct.setProductMaterial(productMaterial);
        newProduct.setProductDescription(productDescription);

        // Define the directory for image uploads
        Resource resource = resourceLoader.getResource("classpath:static/uploadedFiles/img");
        String filePath = null;
        if (!resource.exists()) {
            // 경로가 존재하지 않을 때
            String root = "src/main/resources/static/uploadedFiles/img";

            File file = new File(root);
            file.mkdirs();

            filePath = file.getAbsolutePath();
        } else {
            // 경로가 이미 존재할 때
            filePath = resourceLoader.getResource("classpath:static/uploadedFiles/img")
                    .getFile()
                    .getAbsolutePath();
        }

        // Generate unique names for the uploaded file and thumbnail
        String originalFileName = productImageOriginal.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedName = UUID.randomUUID().toString().replace("-", "") + extension;
        File originalFile = new File(filePath + "/" + savedName);
        String thumbnailName = savedName.replace(extension, "_thumbnail" + extension);
        File thumbnailFile = new File(filePath + "/" + thumbnailName);

        try {
            // Save the uploaded image
            productImageOriginal.transferTo(originalFile);

            // Generate a thumbnail using Thumbnailator
            Thumbnails.of(originalFile)
                    .size(160, 90) // Define thumbnail dimensions
                    .toFile(thumbnailFile);

            // Set paths in ProductDTO
            newProduct.setProductImageOriginal("/uploadedFiles/img/" + savedName);
            newProduct.setProductImageThumbnail("/uploadedFiles/img/" + thumbnailName);

            // Call service to save the product
            Integer result = productAdminService.registNewProduct(newProduct);
            String message;

            if (result == null || result == 0) {
                message = "상품등록에 실패했습니다. 다시 시도해주세요.";
                mv.setViewName("redirect:/admin/product/productRegist");
            } else {
                message = "상품을 성공적으로 등록했습니다.";
                mv.setViewName("redirect:/admin/product/productsManage");
            }

            redirectAttributes.addFlashAttribute("message", message);

        } catch (IOException e) {
            // Handle exceptions and rollback if needed
            if (originalFile.exists()) {
                originalFile.delete();
            }
            if (thumbnailFile.exists()) {
                thumbnailFile.delete();
            }
            System.err.println("[Failed] File upload or thumbnail generation failed!");
            e.printStackTrace();
            mv.setViewName("redirect:/admin/product/productRegist");
            mv.addObject("message", "파일 업로드 중 오류가 발생했습니다. 다시 시도해주세요.");
        }

        return mv;
    }

    @GetMapping("/productDetail/{code}")
    public ModelAndView findProductDetail(@PathVariable("code") int code, ModelAndView mv) {
        ProductDTO product = productAdminService.findProductByCode(code);
        mv.addObject("product", product);
        mv.addObject("activeSection", "productDetail");
        mv.setViewName("admin/layout/adminLayout");

        return mv;
    }

    @PostMapping("/productUpdate") // 수정 요청
    public ModelAndView updateProduct(@RequestParam("productCode") int productCode,
                                      @RequestParam("categoryCode") int categoryCode,
                                      @RequestParam("productName") String productName,
                                      @RequestParam("productPrice") int productPrice,
                                      @RequestParam("productDeliverTime") String productDeliverTime,
                                      @RequestParam("productDelieverCost") int productDelieverCost,
                                      @RequestParam("productSize") String productSize,
                                      @RequestParam("productMaterial") String productMaterial,
                                      @RequestParam("productDescription") String productDescription,
                                      @RequestParam("existingImageOriginal") String existingImageOriginal,
                                      @RequestParam("existingImageThumbnail") String existingImageThumbnail,
                                      @RequestParam("productImageOriginal") MultipartFile productImageOriginal,
                                      RedirectAttributes redirectAttributes,
                                      ModelAndView mv) throws IOException {
        ProductDTO newProduct = new ProductDTO();
        newProduct.setProductCode(productCode);
        newProduct.setCategoryCode(categoryCode);
        newProduct.setProductName(productName);
        newProduct.setProductPrice(productPrice);
        newProduct.setProductDeliverTime(productDeliverTime);
        newProduct.setProductDelieverCost(productDelieverCost);
        newProduct.setProductSize(productSize);
        newProduct.setProductMaterial(productMaterial);
        newProduct.setProductDescription(productDescription);

        Resource resource = resourceLoader.getResource("classpath:static/uploadedFiles/img");
        String filePath = null;
        if (!resource.exists()) {
            // 경로가 존재하지 않을 때
            String root = "src/main/resources/static/uploadedFiles/img";
            File file = new File(root);
            file.mkdirs();
            filePath = file.getAbsolutePath();
        } else {
            // 경로가 이미 존재할 때
            filePath = resource.getFile().getAbsolutePath();
        }

        // Check if a new file is uploaded
        if (!productImageOriginal.isEmpty()) {
            // Generate unique names for the uploaded file and thumbnail
            String originalFileName = productImageOriginal.getOriginalFilename();
            int dotIndex = originalFileName.lastIndexOf(".");
            String extension = dotIndex != -1 ? originalFileName.substring(dotIndex) : "";
            String savedName = UUID.randomUUID().toString().replace("-", "") + extension;
            File originalFile = new File(filePath + "/" + savedName);
            String thumbnailName = savedName.replace(extension, "_thumbnail" + extension);
            File thumbnailFile = new File(filePath + "/" + thumbnailName);

            try {
                // Save the uploaded image
                productImageOriginal.transferTo(originalFile);

                // Generate a thumbnail using Thumbnailator
                Thumbnails.of(originalFile)
                        .size(160, 90) // Define thumbnail dimensions
                        .toFile(thumbnailFile);

                // Set paths in ProductDTO
                newProduct.setProductImageOriginal("/uploadedFiles/img/" + savedName);
                newProduct.setProductImageThumbnail("/uploadedFiles/img/" + thumbnailName);

            } catch (IOException e) {
                // Handle exceptions and rollback if needed
                if (originalFile.exists()) {
                    originalFile.delete();
                }
                if (thumbnailFile.exists()) {
                    thumbnailFile.delete();
                }
                System.err.println("[Failed] File upload or thumbnail generation failed!");
                e.printStackTrace();
                mv.setViewName("redirect:/admin/product/productRegist");
                mv.addObject("message", "파일 업로드 중 오류가 발생했습니다. 다시 시도해주세요.");
                return mv;
            }
        } else {
            // Use existing paths if no new file is uploaded
            newProduct.setProductImageOriginal(existingImageOriginal);
            newProduct.setProductImageThumbnail(existingImageThumbnail);
        }

        // Call service to save the product
        Integer result = productAdminService.updateProduct(newProduct);
        String message;

        if (result == null || result == 0) {
            message = "상품수정에 실패했습니다. 다시 시도해주세요.";
            mv.setViewName("redirect:/admin/product/productRegist");
        } else {
            message = "상품을 성공적으로 수정했습니다.";
            mv.setViewName("redirect:/admin/product/productsManage");
        }

        redirectAttributes.addFlashAttribute("message", message);
        return mv;
    }

    @PostMapping("/productDelete") // 메뉴 삭제
    public ModelAndView deleteProduct(@RequestParam("productCode") int productCode, RedirectAttributes rAttr, ModelAndView mv) {
        Integer result =  productAdminService.deleteProduct(productCode);

        String message;

        if (result == null || result == 0) {
            message = "상품삭제에 실패했습니다. 다시 시도해주세요.";
            mv.setViewName("redirect:/admin/product/productUpdate");
        } else {
            message = "상품을 성공적으로 수정했습니다.";
            mv.setViewName("redirect:/admin/product/productsManage");
        }

        rAttr.addFlashAttribute("message", message);

        return mv;
    }
}
