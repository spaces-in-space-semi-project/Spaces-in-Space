package com.space.spacesinspace.user.pay.controller;

import com.space.spacesinspace.common.dto.MemberDTO;
import com.space.spacesinspace.common.dto.PayDTO;
import com.space.spacesinspace.common.dto.PayDetailDTO;
import com.space.spacesinspace.common.dto.ProductDTO;
import com.space.spacesinspace.user.cart.model.dto.CartDTO;
import com.space.spacesinspace.user.pay.model.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user/pay/*")
public class PayController {


    private final PayService payService;

    @Autowired
    public PayController(PayService payservice) {
        this.payService = payservice;
    }

    // 사용자의 결제한 주문내역을 전체 조회
    @GetMapping("payList")
    public String findPayList(Model mv,
                              @ModelAttribute PayDTO payDTO,
                              @ModelAttribute PayDetailDTO payDetailDTO) {

        List<PayDTO> payList = payService.findPayList();

        mv.addAttribute("payList", payList);
        mv.addAttribute("memberName", "회원");
        mv.addAttribute("activeSection", "order");

        return "/user/member/myPage";
    }


    // 사용자의 주문전체내역 중, 하나를 선택하여 상세 조회
    @GetMapping("findPayDetail/{payCode}")
    public String findPayDetail(@PathVariable("payCode") int payCode, Model mv){

        PayDTO findPayByCode = payService.findPayByCode(payCode);
        List<PayDetailDTO> payDetailList = payService.findPayDetailList(payCode);

        mv.addAttribute("findPayByCode", findPayByCode);
        mv.addAttribute("payDetailList", payDetailList);
        mv.addAttribute("memberName", "회원");
        mv.addAttribute("activeSection", "orderDetail");

        return "/user/member/myPage";
    }

    // 사용자가 상품페이지에서 [구매하기] 누르면 들고 결제진행.
    @PostMapping("payProgress")
    public String payProgress(Model mv,
                              @RequestParam(value = "productCode", required = false, defaultValue = "0")int productCode,
                              @AuthenticationPrincipal MemberDTO member){

        int memberCode = member.getMemberCode();

        ProductDTO payProgress = payService.payProgress(productCode);
        MemberDTO payProgressUser = payService.payProgressUser(memberCode);

        mv.addAttribute("payProgress", payProgress);
        mv.addAttribute("payProgressUser", payProgressUser);

        return "user/pay/payProgress";
    }

    /*주문 상세내역조회 시, 배송전이면 삭제하는 기능 보류*/
    @PostMapping("delete/{payCode}")
    public String deletePayMenu(@PathVariable("payCode") int payCode){
        payService.deletePayMenu(payCode);
        return "redirect:/user/pay/payList";
    }

    /*주문 상세내역조회 시, 배송전이면 결제취소여부 N -> Y 로 기록 수정하며 남기는 기능 */
    @PostMapping("update/{payCode}")
    public String updatePayMenu(@PathVariable("payCode") int payCode){
        payService.updatePayMenu(payCode);
        return "redirect:/user/pay/payList";
    }

    //결제시 DB에 정보 기입 [주문내역 + 상세주문내역]
    @PostMapping("receipt")
    public String addPayList(@ModelAttribute PayDTO payDTO,
                             @ModelAttribute PayDetailDTO payDetailDTO) {

        payService.addPayList(payDTO);
        payDetailDTO.setPayCode(payDTO.getPayCode());
        payService.addPayDetailList(payDetailDTO);


        return "redirect:/user/pay/payList";
    }
}
