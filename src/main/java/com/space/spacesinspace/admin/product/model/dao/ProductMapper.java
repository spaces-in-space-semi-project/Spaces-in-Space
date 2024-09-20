package com.space.spacesinspace.admin.product.model.dao;

import com.space.spacesinspace.common.dto.ProductDTO;
import com.space.spacesinspace.user.product.model.dto.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductDTO> findProduct();

    List<CategoryDTO> findCategory();

    void registNewProduct(ProductDTO newProduct);

    ProductDTO findProductByCode(int code);

    void updateProduct(ProductDTO menu);

    void deleteProduct(int code);
}
