package com.space.spacesinspace.admin.product.model.dao;

import com.space.spacesinspace.common.dto.ProductDTO;
import com.space.spacesinspace.common.dto.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface ProductAdminMapper {
    List<ProductDTO> findAllProduct();

    List<CategoryDTO> findAllCategory();

    @Transactional
    int registNewProduct(ProductDTO newProduct);

    ProductDTO findProductByCode(int code);

    @Transactional
    int updateProduct(ProductDTO menu);

    @Transactional
    int deleteProduct(int code);

    List<ProductDTO> findProductBySearch(String searchValue);
}
