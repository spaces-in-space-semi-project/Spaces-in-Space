package com.space.spacesinspace.admin.product.controller;

import com.space.spacesinspace.admin.product.service.ProductService;
import com.space.spacesinspace.common.dto.ProductDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
    private static final Logger logger = LogManager.getLogger(ProductController.class); // 로그를 기록하기 위한 로거

    private final ProductService productService; // 메뉴 서비스 객체
    private final MessageSource messageSource;

    @Autowired
    public ProductController(ProductService productService, MessageSource messageSource) {
        this.productService = productService;
        this.messageSource = messageSource;
    }

    @GetMapping("/productsManage")    // 전체 조회
    public String findProduct(Model model) {
        List<ProductDTO> menuList = productService.findProduct();
        model.addAttribute("productList", productList);
        return "product/productManage";
    }
}
