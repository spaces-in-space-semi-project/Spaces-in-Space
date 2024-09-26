package com.space.spacesinspace.user.product.controller;

import com.space.spacesinspace.common.dto.ProductDTO;
import com.space.spacesinspace.user.product.model.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user/product/*")
public class ProductController {

    private static final Logger logger = LogManager.getLogger(ProductController.class);

    private final ProductService productService;
    private final MessageSource messageSource;

    @Autowired
    public ProductController(ProductService productService, MessageSource messageSource) {
        this.productService = productService;
        this.messageSource = messageSource;
    }

    @GetMapping("productAll")
    public ModelAndView findAllProducts(ModelAndView mv, RedirectAttributes rAttr) {
        List<ProductDTO> productList = productService.findAllProduct();

        if (productList != null && !productList.isEmpty()) {
            mv.addObject("productList", productList);
            mv.setViewName("/user/product/productList");
        } else {
            rAttr.addFlashAttribute("message", "상품 조회 실패");
            mv.setViewName("redirect:/");
        }

        return mv;
    }

    @GetMapping("productByCategory/{categoryCode}")
    public ModelAndView findProductsByCategory(@PathVariable("categoryCode") int categoryCode, ModelAndView mv, RedirectAttributes rAttr) {
        List<ProductDTO> productList = productService.findProductsByCategory(categoryCode);

        if (productList != null && !productList.isEmpty()) {
            mv.addObject("productList", productList);
            mv.addObject("activeCategory", categoryCode);
            mv.setViewName("/user/product/productList");
        } else {
            rAttr.addFlashAttribute("message", "상품 조회 실패");
            mv.setViewName("redirect:/");
        }

        return mv;
    }

    @GetMapping("/productDetail/{productCode}")
    public ModelAndView findProductDetail(@PathVariable("productCode") int productCode,
                                          ModelAndView mv, RedirectAttributes rAttr,
                                          Authentication authentication) {
        ProductDTO product = productService.findProductByCode(productCode);

        boolean isLoggedIn = authentication != null && authentication.isAuthenticated();

        if (product != null) {
            mv.addObject("product", product);
            mv.addObject("isLoggedIn", isLoggedIn);
            mv.setViewName("/user/product/productDetail");
        } else {
            rAttr.addFlashAttribute("message", "상품 상세 조회 실패");
            mv.setViewName("redirect:/");
        }

        return mv;
    }
}
