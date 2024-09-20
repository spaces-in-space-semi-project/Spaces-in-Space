package com.space.spacesinspace.user.product.model.service;

import com.space.spacesinspace.common.dto.ProductDTO;
import com.space.spacesinspace.user.product.model.dao.ProductMapper1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService1 {

    private final ProductMapper1 productMapper1;

    @Autowired
    public ProductService1(ProductMapper1 productMapper1) {
        this.productMapper1 = productMapper1;
    }

    public List<ProductDTO> findAllProducts() {
        return productMapper1.findAllProduct();
    }

    public ProductDTO findProductByCode(int code) {
        return productMapper1.findProductByCode(code);
    }
}
