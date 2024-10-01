package com.space.spacesinspace.user.cart.controller;

import com.space.spacesinspace.common.dto.MemberDTO;
import com.space.spacesinspace.common.dto.PayDTO;
import com.space.spacesinspace.common.dto.PayDetailDTO;
import com.space.spacesinspace.common.dto.ProductDTO;
import com.space.spacesinspace.user.cart.model.dto.CartDTO;
import com.space.spacesinspace.user.cart.model.service.CartService;
import com.space.spacesinspace.user.pay.model.service.PayService;
import com.space.spacesinspace.user.product.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user/cart/*")
public class CartController {

    private CartService cartService;
    private ProductService productService;
    private PayService payService;

    @Autowired
    public CartController(CartService cartService, ProductService productService, PayService payService) {
        this.cartService = cartService;
        this.productService = productService;
        this.payService = payService;
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

//     사용자가 장바구니에서 [구매하기] 누르면 들고 결제 진행
    @PostMapping("cartPayProgress")
    public ModelAndView cartProgress(ModelAndView mv, @RequestParam(value = "memberCode") int memberCode){

        List<CartDTO> checkMenu = cartService.cartProgress(memberCode);
        CartDTO cnt = cartService.getTotalCntForMember(memberCode);
        CartDTO price = cartService.getTotalPriceForMember(memberCode);
        int totalCartCnt = cnt.getTotalCartCnt();
        int totalCartPrice = price.getTotalCartPrice();
        mv.addObject("checkMenu", checkMenu);
        mv.addObject("totalCartCnt",totalCartCnt);
        mv.addObject("totalCartPrice",totalCartPrice);
        mv.setViewName("user/cart/cartPayProgress");
        return mv;
    }

//    장바구니로 상품 정보 등록
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
    //결제시 DB에 정보 기입 [주문내역 + 상세주문내역]
    @PostMapping("receipt")
    public String addPayList(@ModelAttribute PayDTO payDTO,
                             @ModelAttribute PayDetailDTO payDetailDTO,
                             @ModelAttribute ArrayList<CartDTO> cartDTOList,
                             @RequestParam("payDate") String payDate,
                             @RequestParam("payTotalCnt") int payTotalCnt,
                             @RequestParam("payTotalPrice") int payTotalPrice,
                             @RequestParam("payDeliverStatus") String payDeliverStatus,
                             @RequestParam("payRefundYn") String payRefundYn,
                             @RequestParam("payReceiver") String payReceiver,
                             @RequestParam("payDeliverPhone") String payDeliverPhone,
                             @RequestParam("payAddress") String payAddress,
                             @RequestParam(value = "bankCode", required = false, defaultValue = "") int bankCode,
                             @RequestParam(value = "payAccountNumber", required = false, defaultValue = "") Long payAccountNumber,
                             @RequestParam(value = "cardCompanyCode", required = false, defaultValue = "") int cardCompanyCode,
                             @RequestParam(value = "payCardNumber", required = false, defaultValue = "") Long payCardNumber,
                             Model model){

        payDTO.setPayDate(payDate);
        payDTO.setPayTotalCnt(payTotalCnt);
        payDTO.setPayTotalPrice(payTotalPrice);
        payDTO.setPayDeliverStatus(payDeliverStatus);
        payDTO.setPayRefundYn(payRefundYn);
        payDTO.setPayReceiver(payReceiver);
        payDTO.setPayDeliverPhone(payDeliverPhone);
        payDTO.setPayAddress(payAddress);
        payDTO.setBankCode(bankCode);
        payDTO.setPayAccountNumber(payAccountNumber);
        payDTO.setCardCompanyCode(cardCompanyCode);
        payDTO.setPayCardNumber(payCardNumber);

        model.addAttribute("payDate",payDate);
        model.addAttribute("payTotalCnt",payTotalCnt);
        model.addAttribute("payTotalPrice",payTotalPrice);
        model.addAttribute("payDeliverStatus",payDeliverStatus);
        model.addAttribute("payRefundYn",payRefundYn);
        model.addAttribute("payReceiver",payReceiver);
        model.addAttribute("payDeliverPhone",payDeliverPhone);
        model.addAttribute("payAddress",payAddress);
        model.addAttribute("bankCode",bankCode);
        model.addAttribute("payAccountNumber",payAccountNumber);
        model.addAttribute("cardCompanyCode",cardCompanyCode);
        model.addAttribute("payCardNumber",payCardNumber);

        payService.addPayList(payDTO,payDetailDTO);

        // List<CartDTO> 안의 장바구니 항목들을 순회하며 처리
        for(CartDTO cart : cartDTOList) {
            payDetailDTO.setPayCode(payDTO.getPayCode());
            payDetailDTO.setProductCode(cart.getProductCode());
            payDetailDTO.setPayDetailCnt(cart.getCartCnt());
            payDetailDTO.setPayDetailPrice(cart.getCartPrice());
        }

        int memberCode = payDTO.getMemberCode();
        cartService.deleteCartAllMenu(memberCode);

        return "redirect:/user/pay/payList";
    }
}