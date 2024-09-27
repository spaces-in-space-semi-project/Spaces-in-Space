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

    @GetMapping("payList")
    public ModelAndView findPayList(ModelAndView mv){

        List<PayDTO> payList = payService.findPayList();

        mv.addObject("payList", payList);
        mv.addObject("memberName", "회원");
        mv.addObject("activeSection", "order");
        mv.setViewName("user/member/myPage");

        return mv;
    }

    @GetMapping("/findPayDetail/{payCode}")
    public ModelAndView findPayDetail(@PathVariable("payCode") int payCode, ModelAndView mv){

        PayDetailDTO findPayDetail = payService.findPayDetail(payCode);

        mv.addObject("findPayDetail",findPayDetail);
        mv.addObject("memberName", "회원");
        mv.addObject("activeSection", "orderDetail");
        mv.setViewName("user/member/myPage");

        return mv;
    }


    @PostMapping("payProgress")
    public ModelAndView payProgress(ModelAndView mv,
                                    @RequestParam(value = "productCode", required = false, defaultValue = "0")int productCode,
                                    @AuthenticationPrincipal MemberDTO member){

        int memberCode = member.getMemberCode();

        System.out.println("productCode = " + productCode);
        System.out.println("memberCode = " + memberCode);
        ProductDTO payProgress = payService.payProgress(productCode);
        MemberDTO payProgressUser = payService.payProgressUser(memberCode);


        mv.addObject("payProgress", payProgress);
        mv.addObject("payProgressUser", payProgressUser);

        System.out.println("payProgressUser = " + payProgressUser);
        System.out.println("payProgress = " + payProgress);

        mv.setViewName("user/pay/payProgress");
        return mv;
    }

    /*주문 상세내역조회 시, 배송전이면 삭제*/
    @PostMapping("delete/{payCode}")
    public String deletePayMenu(@PathVariable("payCode") int payCode){
        payService.deletePayMenu(payCode);
        return "redirect:/user/pay/payList";
    }
    
    @PostMapping("receipt")
    public String addPayList(@ModelAttribute PayDTO payDTO){
        System.out.println("payDTO = " + payDTO);
        payService.addPayList(payDTO);
        return "redirect:/user/pay/receipt";
    }



}
