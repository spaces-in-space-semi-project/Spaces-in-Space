package com.space.spacesinspace.admin.product.controller;

import com.space.spacesinspace.admin.product.model.service.ProductAdminService;
import com.space.spacesinspace.common.dto.ProductDTO;
import com.space.spacesinspace.common.dto.CategoryDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("admin/product")
public class ProductAdminController {
    private static final Logger logger = LogManager.getLogger(ProductAdminController.class);

    private final ProductAdminService productAdminService;
    private final MessageSource messageSource;

    @Autowired
    public ProductAdminController(ProductAdminService productAdminService, MessageSource messageSource) {
        this.productAdminService = productAdminService;
        this.messageSource = messageSource;
    }

    @GetMapping("/productsManage")    // 전체 조회
    public String findAllProduct(Model model) {
        List<ProductDTO> productList = productAdminService.findAllProduct();
        model.addAttribute("productList", productList);
        return "admin/product/productsManage";
    }

    @GetMapping("productRegist") // 등록 페이지
    public void registPage() {}

    @GetMapping(value="category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {
        System.out.println("JavaScript 내장 함수인 fetch");
        return productAdminService.findAllCategory();
    }

    @PostMapping("prdouctRegist")
    public String registProduct(ProductDTO newProduct, RedirectAttributes rAttr, Locale locale) {
        productAdminService.registNewProduct(newProduct);
        logger.info("Locale : {}", locale);
        rAttr.addFlashAttribute("successMessage", messageSource.getMessage("registProduct", null, locale));
        return "redirect:/admin/product/productsManage";
    }

    @GetMapping("/productDetail/{code}")
    public String findProductDetail(@PathVariable("code") int code,
                                 Model model) {
        ProductDTO product = productAdminService.findProductByCode(code);
        model.addAttribute("product", product);

        return "admin/product/productDetail";
    }

    @GetMapping("/productEdit/{code}") // 수정 페이지
    public String showEditProductForm(@PathVariable("code") int code,
                                   Model model) {
        ProductDTO product = productAdminService.findProductByCode(code);
        model.addAttribute("product", product);

        return "admin/product/productEdit";
    }

    @PostMapping("/update") // 수정 요청
    public String updateProduct(ProductDTO product, RedirectAttributes rAttr) {
        productAdminService.updateProduct(product);
        rAttr.addFlashAttribute("successMessage", "상품이 성공적으로 수정되었습니다.");
        return "redirect:/admin/product/productDetail/" + product.getProductCode();
    }

    @PostMapping("/delete/{code}") // 메뉴 삭제
    public String deleteProduct(@PathVariable("code") int code,
                             RedirectAttributes rAttr) {

        productAdminService.deleteProduct(code);

        rAttr.addFlashAttribute("successMessage", "메뉴가 성공적으로 삭제되었습니다.");

        return "redirect:/admin/product/productManage";
    }
}
