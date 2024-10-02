package com.space.spacesinspace.user.cart.model.dao;

import com.space.spacesinspace.user.cart.model.dto.CartDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CartMapper {
    List<CartDTO> showCartView(String loggedInUsername);

    int updateCartItem(CartDTO cart);

    void deleteCartMenu(int productCode);

    List<CartDTO> cartProgress(int memberCode);

    int addCartMenu(Map<String, Integer> params);

    CartDTO checkCartItem(Map<String, Integer> params);

    CartDTO getTotalPriceForMember(int memberCode);

    CartDTO getTotalCntForMember(int memberCode);

    void deleteCartAllMenu(int memberCode);


}
