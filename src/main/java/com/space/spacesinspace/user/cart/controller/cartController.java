package com.space.spacesinspace.user.cart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/cart/*")
public class cartController {

    @GetMapping("cartList")
    public String cart(){return "";}


}
