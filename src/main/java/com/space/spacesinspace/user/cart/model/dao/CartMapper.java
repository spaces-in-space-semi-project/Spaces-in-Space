package com.space.spacesinspace.user.cart.model.dao;

import com.space.spacesinspace.common.dto.ProductDTO;
import com.space.spacesinspace.user.cart.model.dto.CartDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Mapper
public interface CartMapper {
    List<CartDTO> showCartView(String loggedInUsername);

    void updateCartItem(int productCode, int cartCnt);

    void deleteCartMenu(int productCode);

    CartDTO cartProgress(int memberCode);

    int addCartMenu(Map<String, Integer> params);

    CartDTO checkCartItem(Map<String, Integer> params);
}
