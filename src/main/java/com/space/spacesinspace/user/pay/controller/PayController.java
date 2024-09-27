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
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView findPayList(ModelAndView mv){

        List<PayDTO> payList = payService.findPayList();

        mv.addObject("payList", payList);
        mv.addObject("memberName", "회원");
        mv.addObject("activeSection", "order");
        mv.setViewName("user/member/myPage");

        return mv;
    }

    // 사용자의 주문전체내역 중, 하나를 선택하여 상세 조회
    @GetMapping("/findPayDetail/{payCode}")
    public ModelAndView findPayDetail(@PathVariable("payCode") int payCode, ModelAndView mv){

        PayDetailDTO findPayDetail = payService.findPayDetail(payCode);

        mv.addObject("findPayDetail",findPayDetail);
        mv.addObject("memberName", "회원");
        mv.addObject("activeSection", "orderDetail");
        mv.setViewName("user/member/myPage");

        return mv;
    }

    // 사용자가 상품페이지에서 [구매하기] 누르면 들고 넘어간다.
    @PostMapping("payProgress")
    public ModelAndView payProgress(ModelAndView mv,
                                    @RequestParam(value = "productCode", required = false, defaultValue = "0")int productCode,
                                    @AuthenticationPrincipal MemberDTO member){

        int memberCode = member.getMemberCode();

        ProductDTO payProgress = payService.payProgress(productCode);
        MemberDTO payProgressUser = payService.payProgressUser(memberCode);

        mv.addObject("payProgress", payProgress);
        mv.addObject("payProgressUser", payProgressUser);

        mv.setViewName("user/pay/payProgress");
        return mv;
    }

    /*주문 상세내역조회 시, 배송전이면 삭제*/
    @PostMapping("delete/{payCode}")
    public String deletePayMenu(@PathVariable("payCode") int payCode){
        payService.deletePayMenu(payCode);
        return "redirect:/user/pay/payList";
    }

    //영수증 출력을 향한 여행. 피 카 츄.
    @PostMapping("receipt")
    public String addPayList(@ModelAttribute PayDTO payDTO,
                              @ModelAttribute PayDetailDTO payDetailDTO){

        payService.addPayList(payDTO);
        payService.addPayDetailList(payDetailDTO);

        return "redirect:/user/pay/payList";
    }



}
