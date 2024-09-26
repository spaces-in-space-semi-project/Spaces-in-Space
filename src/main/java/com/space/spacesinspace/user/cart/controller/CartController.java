package com.space.spacesinspace.user.cart.controller;

import com.space.spacesinspace.user.cart.model.dto.CartDTO;
import com.space.spacesinspace.user.cart.model.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("user/cart/*")
public class CartController {

    private CartService cartService;

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

    // 수량 변경  , 이거 우선순위 뒤로 미룸. 원룸이면 1개만 사라.
    @PostMapping(value="update", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public void updateCartItem(@RequestBody CartDTO cartDTO) {
        System.out.println("cartDTO = " + cartDTO);
        cartService.updateCartItem(cartDTO.getProductCode(), cartDTO.getCartCnt());
    }

    // 장바구니 가차없이 삭제.
    @PostMapping("/delete/{productCode}")
    public String deleteCartMenu(@PathVariable("productCode") int productCode){
        cartService.deleteCartMenu(productCode);
        return "redirect:/user/cart/cartList";
    }

    // 장바구니 목록 들고 결제 진행
    @PostMapping("payProgress")
    public ModelAndView cartProgress(ModelAndView mv, int memberCode){

        List<CartDTO> checkMenu = cartService.cartProgress(memberCode);


        for (CartDTO menu : checkMenu) {
            System.out.println(menu);
        }
        
        mv.addObject("checkMenu",checkMenu);
        mv.setViewName("user/pay/payProgress");
        return mv;
    }


}