package com.space.spacesinspace.user.cart.model.service;


import com.space.spacesinspace.user.cart.model.dao.CartMapper;
import com.space.spacesinspace.user.cart.model.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private CartMapper cartMapper;

    @Autowired
    public CartService(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    public String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();  // 로그인한 사용자 ID 반환
        }
        return null;  // 로그인이 되어 있지 않으면 null 반환
    }

    public List<CartDTO> showCartView() {
        String loggedInUsername = getLoggedInUsername();  // 로그인된 사용자 ID 가져오기
        if (loggedInUsername != null) {
            return cartMapper.showCartView(loggedInUsername);  // 로그인된 사용자 ID로 쿼리 실행
        } else {
            return new ArrayList<>();  // 로그인이 안 된 경우 빈 리스트 반환
        }
    }

    @Transactional
    public void updateCartItem(int productCode, int cartCnt) {
        cartMapper.updateCartItem(productCode,cartCnt);
    }


    @Transactional
    public void deleteCartMenu(int productCode) {
        cartMapper.deleteCartMenu(productCode);
    }

    public CartDTO cartProgress(int memberCode) {
        return cartMapper.cartProgress(memberCode);
    }
}
