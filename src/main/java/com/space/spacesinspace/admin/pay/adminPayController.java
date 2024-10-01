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

    /*관리자 전체 주문 내역 조회*/
    @GetMapping("adminPayList")
    public String showPayList(Model mv,
                              @ModelAttribute PayDTO payDTO,
                              @ModelAttribute PayDetailDTO payDetailDTO){

        List<PayDTO> payList = payService.showPayList();

        mv.addAttribute("payList",payList);
        mv.addAttribute("activeSection", "adminOrder");
        return "admin/layout/adminLayout";
    }

    /*관리자 전체 주문 내역 중 검색 (주문번호, 회원아이디, 회원번호 )*/
    @GetMapping("searchPayList")
    public ModelAndView searchPayList(ModelAndView mv, @RequestParam String searchValue) {

        List<PayDTO> searchPayList = payService.searchPayList(searchValue);

        mv.addObject("payList", searchPayList);
        mv.addObject("activeSection","adminOrder");
        mv.setViewName("admin/layout/adminLayout");
        return mv;
    }

    /*관리자 주문 전체 내역 중, 골라서 1개 상세 조회*/
    @GetMapping("adminPayDetail/{payCode}")
    public String findAdminPayDetail(@PathVariable("payCode") int payCode, Model mv) {

        PayDTO findPayByCode = payService.findPayByCode(payCode);
        List<PayDetailDTO> payDetailList = payService.findPayDetailList(payCode);

        mv.addAttribute("findPayByCode", findPayByCode);
        mv.addAttribute("payDetailList", payDetailList);
        mv.addAttribute("activeSection", "adminOrderDetail");

        return "admin/layout/adminLayout";
    }

    /*관리자 주문 상세 내역 중 배송전 이면 취소 삭제 기능 ( 보류 )*/
    @PostMapping("delete/{payCode}")
    public String deleteAdminPayMenu(@PathVariable("payCode") int payCode){
        payService.deleteAdminPayMenu(payCode);
        return "redirect:/admin/pay/adminPayList";
    }

    @PostMapping("update/{payCode}")
    public String updateAdminPayMenu(@PathVariable("payCode") int payCode){
        payService.updateAdminPayMenu(payCode);
        return "redirect:/admin/pay/adminPayList";
    }
}
