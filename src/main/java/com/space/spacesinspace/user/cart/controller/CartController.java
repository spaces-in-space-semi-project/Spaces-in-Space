package com.space.spacesinspace.user.cart.controller;

import com.space.spacesinspace.common.dto.MemberDTO;
import com.space.spacesinspace.common.dto.ProductDTO;
import com.space.spacesinspace.user.cart.model.dto.CartDTO;
import com.space.spacesinspace.user.cart.model.service.CartService;
import com.space.spacesinspace.user.product.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping(value= "addCartMenu", consumes = "application/json", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Map<String, String>> addCartMenu(@AuthenticationPrincipal MemberDTO member,
                                                           @RequestBody Map<String, Integer> params) {
        int productCode = params.get("productCode");
        ProductDTO product = productService.findProductByCode(productCode);
        int productPrice = product.getProductPrice();
        int memberCode = member.getMemberCode();

        params.put("productCode", productCode);
        params.put("memberCode", memberCode);
        params.put("productPrice", productPrice);

        CartDTO cartItem = cartService.checkCartItem(params);

        Map<String, String> response = new HashMap<>();
        if (cartItem == null) {
            Integer result = cartService.addCartMenu(params);

            if (result == null || result == 0) {
                response.put("message", "장바구니 추가에 실패했습니다. 다시 시도해주세요.");
            } else if (result >= 1) {
                response.put("message", "장바구니 추가가 성공적으로 완료되었습니다.");
            } else {
                response.put("message", "알 수 없는 오류가 발생했습니다. 다시 시도해보시거나 관리자에게 문의해주세요.");
            }
        } else {
            response.put("message", "이미 장바구니에 추가된 상품입니다.");
        }

        return ResponseEntity.ok(response);
    }

}