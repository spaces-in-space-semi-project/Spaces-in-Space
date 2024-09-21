package com.space.spacesinspace.user.cart.model.service;


import com.space.spacesinspace.user.cart.model.dao.CartMapper;
import com.space.spacesinspace.user.cart.model.dto.CartDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    CartMapper cartMapper;

    public CartService(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    public List<CartDTO> showCartView() {

        return cartMapper.showCartView();

    }
}
