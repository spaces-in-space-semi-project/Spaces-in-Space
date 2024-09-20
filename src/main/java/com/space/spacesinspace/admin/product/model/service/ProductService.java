package com.space.spacesinspace.admin.product.model.service;

import com.space.spacesinspace.admin.product.model.dao.ProductMapper;
import com.space.spacesinspace.common.dto.ProductDTO;
import com.space.spacesinspace.user.product.model.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public List<ProductDTO> findProduct() {
        return productMapper.findProduct();
    }

    public List<CategoryDTO> findCategory() {
        return productMapper.findCategory();
    }

    @Transactional
    public void registNewProduct(ProductDTO newProduct) {
        productMapper.registNewProduct(newProduct);
    }

    public ProductDTO findProductByCode(int code) {
        return productMapper.findProductByCode(code);
    }

    @Transactional
    public void updateProduct(ProductDTO product) {
        productMapper.updateProduct(product);
    }

    @Transactional
    public void deleteProduct(int code) {
        productMapper.deleteProduct(code);
    }
}
