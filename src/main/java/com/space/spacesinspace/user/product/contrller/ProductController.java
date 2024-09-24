package com.space.spacesinspace.user.product.contrller;

import com.space.spacesinspace.common.dto.ProductDTO;
import com.space.spacesinspace.user.product.model.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/productAll")
    public String findAllProducts(Model model) {
        List<ProductDTO> productList = productService.findAllProduct();
        model.addAttribute("productList", productList);
        return "user/product/productAll";
    }

    @GetMapping("/productDetail/{code}")
    public String findProductDetail(@PathVariable("code") int code,
                                    Model model) {
        ProductDTO product = productService.findProductByCode(code);
        model.addAttribute("product", product);

        return "user/product/productDetail";
    }
}
