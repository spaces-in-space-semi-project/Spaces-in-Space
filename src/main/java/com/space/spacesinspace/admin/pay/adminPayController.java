package com.space.spacesinspace.admin.pay;

import com.space.spacesinspace.common.dto.PayDTO;
import com.space.spacesinspace.common.dto.PayDetailDTO;
import com.space.spacesinspace.user.pay.model.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/pay/*")
public class adminPayController {

    private PayService payService;

    @Autowired
    public adminPayController(PayService payService) {
        this.payService = payService;
    }

    @GetMapping("adminpaylist")
    public ModelAndView showPayList(ModelAndView mv){

        List<PayDTO> payList = payService.showPayList();

        mv.addObject("payList",payList);
        mv.setViewName("admin/pay/adminPayList");
        return mv;
    }

    @GetMapping("/adminPayDetail/{payCode}")
    public ModelAndView findAdminPayDetail(@PathVariable("payCode") int payCode, ModelAndView mv) {

        PayDetailDTO findAdminPayDetail = payService.findAdminPayDetail(payCode);

        mv.addObject("findAdminPayDetail", findAdminPayDetail);
        mv.addObject("memberName", "회원");
        mv.setViewName("admin/pay/adminPayDetail");

        return mv;
    }

    @PostMapping("delete/{payCode}")
    public String deleteAdminPayMenu(@PathVariable("payCode") int payCode){
        payService.deleteAdminPayMenu(payCode);
        return "redirect:/admin/pay/adminPayList";
    }
}
