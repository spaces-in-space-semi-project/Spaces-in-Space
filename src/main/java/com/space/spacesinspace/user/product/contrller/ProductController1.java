package com.space.spacesinspace.user.product.contrller;

import com.space.spacesinspace.common.dto.ProductDTO;
import com.space.spacesinspace.user.product.model.service.ProductService1;
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
@RequestMapping("/user/product")
public class ProductController1 {

    private static final Logger logger = LogManager.getLogger(ProductController1.class);

    private final ProductService1 productService1;
    private final MessageSource messageSource;

    @Autowired
    public ProductController1(ProductService1 productService1, MessageSource messageSource) {
        this.productService1 = productService1;
        this.messageSource = messageSource;
    }

    @GetMapping("/productAll")
    public String findAllProducts(Model model) {
        List<ProductDTO> productList = productService1.findAllProducts();
        model.addAttribute("productList", productList);
        return "user/product/productAll";
    }

    public String findProductDetail(@PathVariable("code") int code,
                                    Model model) {
        ProductDTO product = productService1.findProductByCode(code);
        model.addAttribute("product", product);

        return "product/productDetail";
    }
}
