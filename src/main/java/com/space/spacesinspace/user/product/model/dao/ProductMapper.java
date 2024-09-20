package com.space.spacesinspace.user.product.model.dao;

import com.space.spacesinspace.common.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductDTO> findAllProduct();

    ProductDTO findProductByCode(int code);
}
