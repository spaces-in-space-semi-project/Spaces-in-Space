package com.space.spacesinspace.admin.product.model.service;

import com.space.spacesinspace.admin.product.model.dao.ProductAdminMapper;
import com.space.spacesinspace.common.dto.ProductDTO;
import com.space.spacesinspace.common.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductAdminService {

    private final ProductAdminMapper productAdminMapper;

    @Autowired
    public ProductAdminService(ProductAdminMapper productAdminMapper) {
        this.productAdminMapper = productAdminMapper;
    }

    public List<ProductDTO> findAllProduct() {
        return productAdminMapper.findAllProduct();
    }

    public List<CategoryDTO> findAllCategory() {
        return productAdminMapper.findAllCategory();
    }

    @Transactional
    public void registNewProduct(ProductDTO newProduct) {
        productAdminMapper.registNewProduct(newProduct);
    }

    public ProductDTO findProductByCode(int code) {
        return productAdminMapper.findProductByCode(code);
    }

    @Transactional
    public void updateProduct(ProductDTO product) {
        productAdminMapper.updateProduct(product);
    }

    @Transactional
    public void deleteProduct(int code) {
        productAdminMapper.deleteProduct(code);
    }
}
