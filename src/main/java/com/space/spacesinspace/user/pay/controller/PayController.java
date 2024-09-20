package com.space.spacesinspace.user.pay.controller;

import com.space.spacesinspace.common.dto.PayDTO;
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

    @GetMapping("payList")
    public void payList() {}

    @PostMapping("payList")
    public ModelAndView findPayList(ModelAndView model){

        List<PayDTO> payList = payService.findPayList();

        model.addObject("payList", payList);
        model.addObject("activeSection", "order");
        model.setViewName("payList");
        return model;
    }

    @GetMapping("findPayDetail/{payCode}")
    public String findPayDetail(@PathVariable("payCode") int payCode, Model model){

        PayDTO findPayDetail = payService.findPayDetail(payCode);

        model.addAttribute("findPayDetail",findPayDetail);

        return "findPayDetail";
    }

}
