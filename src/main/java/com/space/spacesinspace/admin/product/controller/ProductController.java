package com.space.spacesinspace.admin.product.controller;

import com.space.spacesinspace.admin.product.model.service.ProductService;
import com.space.spacesinspace.common.dto.ProductDTO;
import com.space.spacesinspace.user.product.model.dto.CategoryDTO;
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
@RequestMapping("/admin/product")
public class ProductController {
    private static final Logger logger = LogManager.getLogger(ProductController.class);

    private final ProductService productService;
    private final MessageSource messageSource;

    @Autowired
    public ProductController(ProductService productService, MessageSource messageSource) {
        this.productService = productService;
        this.messageSource = messageSource;
    }

    @GetMapping("/productsManage")    // 전체 조회
    public String findProduct(Model model) {
        List<ProductDTO> productList = productService.findProduct();
        model.addAttribute("productList", productList);
        return "product/productsManage";
    }

    @GetMapping("productRegist") // 등록
    public void registPage() {}

    @GetMapping(value="category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {
        return productService.findCategory();
    }

    @PostMapping("prdouctRegist")
    public String registMenu(ProductDTO newProduct, RedirectAttributes rAttr, Locale locale) {
        productService.registNewProduct(newProduct);
        logger.info("Locale : {}", locale);


        rAttr.addFlashAttribute("successMessage", messageSource.getMessage("registMenu", null, locale));
        return "redirect:/product/productsManage";
    }

    @GetMapping("/productDetail/{code}")
    public String findProductDetail(@PathVariable("code") int code,
                                 Model model) {
        ProductDTO product = productService.findProductByCode(code);
        model.addAttribute("product", product);

        return "product/productDetail";
    }

    @GetMapping("/ProductEdit/{code}") // 수정 페이지
    public String showEditProductForm(@PathVariable("code") int code,
                                   Model model) {
        ProductDTO product = productService.findProductByCode(code);
        model.addAttribute("product", product);

        return "product/productEdit";
    }

    @PostMapping("/update") // 수정 요청
    public String updateMenu(ProductDTO product, RedirectAttributes rAttr) {

        productService.updateProduct(product);
        rAttr.addFlashAttribute("successMessage", "상품이 성공적으로 수정되었습니다.");
        return "redirect:/product/productDetail/" + product.getProductCode();
    }

    @PostMapping("/delete/{code}") // 메뉴 삭제
    public String deleteMenu(@PathVariable("code") int code,
                             RedirectAttributes rAttr) {

        productService.deleteProduct(code);

        rAttr.addFlashAttribute("successMessage", "메뉴가 성공적으로 삭제되었습니다.");

        return "redirect:/product/productManage";
    }
}
