package com.space.spacesinspace.user.pay.controller;

import com.space.spacesinspace.common.dto.PayDTO;
import com.space.spacesinspace.common.dto.PayDetailDTO;
import com.space.spacesinspace.user.pay.model.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @GetMapping("payList")
//    public String payList() {return "user/pay/payList";}

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

}
