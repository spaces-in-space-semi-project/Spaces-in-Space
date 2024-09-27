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

    // 전체 조회
    @GetMapping("productsManage")
    public ModelAndView findAllProduct(ModelAndView mv) {
        List<ProductDTO> productList = productAdminService.findAllProduct();
        mv.addObject("productList", productList);
        mv.addObject("activeSection", "product");
        mv.setViewName("admin/layout/adminLayout");
        return mv;
    }

    // 카테고리 조회
    @GetMapping(value = "findCategory", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {
        return productAdminService.findAllCategory();
    }

    // 상품 등록 페이지 이동
    @GetMapping("productRegist")
    public ModelAndView registProduct(ModelAndView mv) {
        mv.addObject("activeSection", "productRegist");
        mv.setViewName("admin/layout/adminLayout");
        return mv;
    }

    // 상품 등록
    @PostMapping("productRegist")
    public ModelAndView registProduct(@RequestParam("categoryCode") int categoryCode,
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

        // directory 선언
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

        // 랜덤으로 유니크한 파일명 생성
        String originalFileName = productImageOriginal.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedName = UUID.randomUUID().toString().replace("-", "") + extension;
        File originalFile = new File(filePath + "/" + savedName);
        String thumbnailName = savedName.replace(extension, "_thumbnail" + extension);
        File thumbnailFile = new File(filePath + "/" + thumbnailName);

        try {
            // 파일 저장
            productImageOriginal.transferTo(originalFile);

            // Thumbnailator 사용하여 썸네일 생성
            Thumbnails.of(originalFile)
                    .size(160, 90) // Define thumbnail dimensions
                    .toFile(thumbnailFile);

            // DTO에 저장된 파일의 경로를 String으로 저장
            newProduct.setProductImageOriginal("/uploadedFiles/img/" + savedName);
            newProduct.setProductImageThumbnail("/uploadedFiles/img/" + thumbnailName);

            // 상품 등록 로직
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
            System.err.println("[Failed] 파일 업로드 또는 썸네일 생성 실패");
            e.printStackTrace();

            redirectAttributes.addFlashAttribute("message", "파일 업로드 중 오류가 발생했습니다. 다시 시도해주세요.");
            mv.setViewName("redirect:/admin/product/productRegist");
        }

        return mv;
    }

    // 상품 상제 조회
    @GetMapping("productDetail/{code}")
    public ModelAndView findProductDetail(@PathVariable("code") int code, ModelAndView mv) {
        ProductDTO product = productAdminService.findProductByCode(code);
        mv.addObject("product", product);
        mv.addObject("activeSection", "productDetail");
        mv.setViewName("admin/layout/adminLayout");

        return mv;
    }

    // 상품 수정
    @PostMapping("productUpdate")
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

        // 새로운 파일이 업로드 되었을 때만 동작
        if (!productImageOriginal.isEmpty()) {
            String originalFileName = productImageOriginal.getOriginalFilename();
            int dotIndex = originalFileName.lastIndexOf(".");
            String extension = dotIndex != -1 ? originalFileName.substring(dotIndex) : "";
            String savedName = UUID.randomUUID().toString().replace("-", "") + extension;
            File originalFile = new File(filePath + "/" + savedName);
            String thumbnailName = savedName.replace(extension, "_thumbnail" + extension);
            File thumbnailFile = new File(filePath + "/" + thumbnailName);

            try {
                // 업로드 된 이미지 저장
                productImageOriginal.transferTo(originalFile);

                // Thumbnailator로 썸네일 생성
                Thumbnails.of(originalFile)
                        .size(160, 90) // Define thumbnail dimensions
                        .toFile(thumbnailFile);

                // ProductDTO에 경로 담기
                newProduct.setProductImageOriginal("/uploadedFiles/img/" + savedName);
                newProduct.setProductImageThumbnail("/uploadedFiles/img/" + thumbnailName);

            } catch (IOException e) {
                // 오류가 났을 경우 파일 삭제
                if (originalFile.exists()) {
                    originalFile.delete();
                }
                if (thumbnailFile.exists()) {
                    thumbnailFile.delete();
                }
                System.err.println("[Failed] 파일 업로드 또는 썸네일 생성 실패");
                e.printStackTrace();
                mv.setViewName("redirect:/admin/product/productRegist");

                redirectAttributes.addFlashAttribute("message", "파일 업로드 중 오류가 발생했습니다. 다시 시도해주세요.");
                return mv;
            }
        } else {
            // 새로운 파일이 없으면 기존의 경로 ProductDTO에 담기
            newProduct.setProductImageOriginal(existingImageOriginal);
            newProduct.setProductImageThumbnail(existingImageThumbnail);
        }

        // 업데이트된 정보 저장
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

    // 상품 삭제
    @PostMapping("/productDelete")
    public ModelAndView deleteProduct(@RequestParam("productCode") int productCode, RedirectAttributes rAttr, ModelAndView mv) {
        Integer result =  productAdminService.deleteProduct(productCode);

        String message;

        if (result == null || result == 0) {
            message = "상품 삭제에 실패했습니다. 다시 시도해주세요.";
            mv.setViewName("redirect:/admin/product/productUpdate");
        } else {
            message = "상품을 성공적으로 삭제했습니다.";
            mv.setViewName("redirect:/admin/product/productsManage");
        }

        rAttr.addFlashAttribute("message", message);

        return mv;
    }
}
