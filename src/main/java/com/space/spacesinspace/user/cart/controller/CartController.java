package com.space.spacesinspace.user.cart.controller;

import com.space.spacesinspace.common.dto.MemberDTO;
import com.space.spacesinspace.common.dto.ProductDTO;
import com.space.spacesinspace.user.cart.model.dto.CartDTO;
import com.space.spacesinspace.user.cart.model.service.CartService;
import com.space.spacesinspace.user.product.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("user/cart/*")
public class CartController {

    private CartService cartService;
    private ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }


    // 장바구니 목록 전체 조회
    @GetMapping("cartList")
    public ModelAndView showCartView(ModelAndView mv, @AuthenticationPrincipal MemberDTO member){
        int memberCode = member.getMemberCode();

        List<CartDTO> cart = cartService.showCartView();

        mv.addObject("cart", cart);
        mv.addObject("memberName", "회원");
        mv.addObject("memberCode", memberCode);
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

//     장바구니 목록 들고 결제 진행
    @GetMapping("cartPayProgress")
    public ModelAndView cartProgress(ModelAndView mv, @RequestParam(value = "memberCode") int memberCode){

        CartDTO checkMenu = cartService.cartProgress(memberCode);
        mv.addObject("checkMenu", checkMenu);
        mv.setViewName("user/cart/cartPayProgress");
        System.out.println("checkMenu = " + checkMenu);
        return mv;
    }

    @PostMapping("addCartMenu")
    public ModelAndView addCartMenu(ModelAndView mv, @AuthenticationPrincipal MemberDTO member,
                                    @RequestParam(value="productCode") int productCode){

        ProductDTO product = productService.findProductByCode(productCode);
        int getProductCode = product.getProductCode();
        int productPrice = product.getProductPrice();
        int memberCode = member.getMemberCode();

        System.out.println("productPrice = " + productPrice);
        System.out.println("memberCode = " + memberCode);
        System.out.println("getProductCode = " + getProductCode);

//        CartDTO addCartMenu = cartService.addCartMenu(productCode);

//        mv.addObject("addCartMenu",addCartMenu);
        mv.addObject("productPrice",productPrice);
        mv.addObject("productCode",getProductCode);
        mv.addObject("memberCode",memberCode);
        mv.setViewName("redirect:/user/cart/cartList");
        return mv;
    }

}