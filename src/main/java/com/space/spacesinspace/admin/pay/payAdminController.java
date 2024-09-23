package com.space.spacesinspace.admin.pay;

import com.space.spacesinspace.common.dto.PayDTO;
import com.space.spacesinspace.user.pay.model.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/pay/*")
public class payAdminController {

    PayService payService;

    @Autowired
    public payAdminController(PayService payService) {
        this.payService = payService;
    }

    @GetMapping("adminpaylist")
    public ModelAndView showPayList(ModelAndView mv){

        List<PayDTO> payList = payService.showPayList();

        mv.addObject("payList",payList);
        mv.setViewName("/admin/pay/adminpaylist");

        return mv;
    }
}
