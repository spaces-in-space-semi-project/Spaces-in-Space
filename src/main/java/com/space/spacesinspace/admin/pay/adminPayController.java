package com.space.spacesinspace.admin.pay;

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
@RequestMapping("/admin/pay/*")
public class adminPayController {

    private PayService payService;

    @Autowired
    public adminPayController(PayService payService) {
        this.payService = payService;
    }

    @GetMapping("adminPayList")
    public String showPayList(Model mv,
                                    @ModelAttribute PayDTO payDTO,
                                    @ModelAttribute PayDetailDTO payDetailDTO){

        List<PayDTO> payList = payService.showPayList();

        mv.addAttribute("payList",payList);
        mv.addAttribute("activeSection", "adminOrder");
        return "admin/layout/adminLayout";
    }

    @GetMapping("adminPayDetail/{payCode}")
    public String findAdminPayDetail(@PathVariable("payCode") int payCode, Model mv) {

        PayDTO findPayByCode = payService.findPayByCode(payCode);
        List<PayDetailDTO> payDetailList = payService.findPayDetailList(payCode);

        mv.addAttribute("findPayByCode", findPayByCode);
        mv.addAttribute("payDetailList", payDetailList);
        mv.addAttribute("activeSection", "adminOrderDetail");

        return "admin/layout/adminLayout";
    }

    @PostMapping("delete/{payCode}")
    public String deleteAdminPayMenu(@PathVariable("payCode") int payCode){
        payService.deleteAdminPayMenu(payCode);
        return "redirect:/admin/pay/adminPayList";
    }
}
