package com.space.spacesinspace.user.cart.controller;

import com.space.spacesinspace.user.cart.model.dto.CartDTO;
import com.space.spacesinspace.user.cart.model.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user/cart/*")
public class CartController {

    CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // 장바구니 목록 전체 조회
    @GetMapping("cartList")
    public ModelAndView showCartView(ModelAndView mv){

        List<CartDTO> cart = cartService.showCartView();

        mv.addObject("cart",cart);
        mv.addObject("memberName", "회원");
        mv.addObject("activeSection", "cart");
        mv.setViewName("user/member/myPage");

        return mv;
    }

    // 수량 변경 처리
    @PostMapping(value="update", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public void updateCartItem(@RequestBody CartDTO cartDTO) {
        System.out.println("cartDTO = " + cartDTO);
        cartService.updateCartItem(cartDTO.getProductCode(), cartDTO.getCartCnt());
    }

    // 삭제 처리
    @PostMapping("delete")
    @ResponseBody
    public void deleteCartItem(@RequestBody Map<String, String> payload) {
        String productCode = payload.get("id");
        cartService.deleteCartItem(productCode);
    }

}
