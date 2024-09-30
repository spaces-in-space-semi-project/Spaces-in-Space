package com.space.spacesinspace.admin.product.model.service;

import com.space.spacesinspace.admin.product.model.dao.ProductAdminMapper;
import com.space.spacesinspace.common.dto.ProductDTO;
import com.space.spacesinspace.common.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
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
    public Integer registNewProduct(ProductDTO newProduct) {
        Integer result = null;

        try {
            result = productAdminMapper.registNewProduct(newProduct);
        } catch (DuplicateKeyException e) {
            result = 0;
            e.printStackTrace();
        } catch (BadSqlGrammarException e) {
            result = 0;
            e.printStackTrace();
        }

        return result;
    }

    public ProductDTO findProductByCode(int code) {
        return productAdminMapper.findProductByCode(code);
    }

    @Transactional
    public Integer updateProduct(ProductDTO product) {
        Integer result = null;

        try {
            result = productAdminMapper.updateProduct(product);
        } catch (DuplicateKeyException | BadSqlGrammarException e) {
            result = 0;
            e.printStackTrace();
        }

        return result;
    }

    @Transactional
    public Integer deleteProduct(int code) {
        return productAdminMapper.deleteProduct(code);
    }

    public List<ProductDTO> findProductBySearch(String searchValue) {
        return productAdminMapper.findProductBySearch(searchValue);
    }
}
