package com.space.spacesinspace.user.cart.controller;

import com.space.spacesinspace.user.cart.model.dto.CartDTO;
import com.space.spacesinspace.user.cart.model.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("user/cart/*")
public class cartController {

    CartService cartService;

    @Autowired
    public cartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("cartList")
    public ModelAndView showCartView(ModelAndView mv){

        List<CartDTO> cart = cartService.showCartView();

        mv.addObject("cart",cart);
        mv.setViewName("user/cart/cartList");

        return mv;
    }


}
