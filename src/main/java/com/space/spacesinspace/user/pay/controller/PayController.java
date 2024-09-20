package com.space.spacesinspace.user.pay.controller;

import com.space.spacesinspace.common.dto.PayDTO;
import com.space.spacesinspace.user.pay.model.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PayController {

    private final PayService payService;

    @Autowired
    public PayController(PayService payservice) {
        this.payService = payservice;
    }

    @GetMapping("user/pay/paylist")
    public void payList() {}

    @RequestMapping(value = "redirect:/",method = RequestMethod.POST)
    public String findPayList(ModelAndView model){

        List<PayDTO> payList = payService.findPayList();

        model.addObject("payList",payList);
        model.setViewName("user/pay/paylist");
        return "redirect:/";
    }

    @GetMapping("user/pay/paylist/{payCode}")
    public String findPayDetail(@PathVariable int payCode, Model model){

        List<PayDTO> findPayDetail = payService.findPayDetail(payCode);

        model.addAttribute("findPayDetail",findPayDetail);

        return "user/pay/findPayDetail";
    }

}
