package com.space.spacesinspace.user.cart.model.dao;

import com.space.spacesinspace.common.dto.ProductDTO;
import com.space.spacesinspace.user.cart.model.dto.CartDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    List<CartDTO> showCartView(String loggedInUsername);

    void updateCartItem(int productCode, int cartCnt);

    void deleteCartMenu(int productCode);

    CartDTO cartProgress(int memberCode);

    CartDTO addCartMenu(int productCode);
}
