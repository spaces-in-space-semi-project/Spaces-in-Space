package com.space.spacesinspace.admin.product.model.dao;

import com.space.spacesinspace.common.dto.ProductDTO;
import com.space.spacesinspace.common.dto.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductAdminMapper {
    List<ProductDTO> findAllProduct();

    List<CategoryDTO> findAllCategory();

    void registNewProduct(ProductDTO newProduct);

    ProductDTO findProductByCode(int code);

    void updateProduct(ProductDTO menu);

    void deleteProduct(int code);
}
