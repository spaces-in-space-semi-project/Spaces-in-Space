package com.space.spacesinspace.user.product.model.service;

import com.space.spacesinspace.common.dto.ProductDTO;
import com.space.spacesinspace.user.product.model.dao.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public List<ProductDTO> findAllProduct() {
        return productMapper.findAllProduct();
    }

    public ProductDTO findProductByCode(int productCode) {
        return productMapper.findProductByCode(productCode);
    }

    public List<ProductDTO> findProductsByCategory(int categoryCode) {
        return productMapper.findProductsByCategory(categoryCode);
    }
}
