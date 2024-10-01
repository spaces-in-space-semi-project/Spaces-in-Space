package com.space.spacesinspace.admin.inquiry.controller;

import com.space.spacesinspace.admin.inquiry.model.service.AdminInquiryService;
import com.space.spacesinspace.common.dto.InquiryDTO;
import com.space.spacesinspace.common.dto.ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/inquiry/*")
public class AdminInquiryController {

    private final AdminInquiryService adminInquiryService;

    @Autowired
    public AdminInquiryController(AdminInquiryService adminInquiryService) {
        this.adminInquiryService = adminInquiryService;
    }

    @GetMapping("/list")
    public ModelAndView findAllInquiry(ModelAndView mv) {

        List<InquiryDTO> inquiryList = adminInquiryService.findAllInquiry();

        mv.addObject("inquiryList", inquiryList);
        mv.addObject("activeSection", "inquiry");
        mv.setViewName("admin/layout/adminLayout");

        return mv;
    }

    @GetMapping("searchInquiry")
    public ModelAndView searchInquiry(ModelAndView mv, @RequestParam String searchValue) {
        List<InquiryDTO> searchedInquiryList = adminInquiryService.findInquiryBySearch(searchValue);
        mv.addObject("inquiryList", searchedInquiryList);
        mv.addObject("activeSection", "inquiry");
        mv.setViewName("admin/layout/adminLayout");
        return mv;
    }

    @GetMapping("/detail/{inquiryCode}")
    public ModelAndView findInquiryByCode(@PathVariable("inquiryCode") int inquiryCode,
                                          ModelAndView mv) {

        InquiryDTO inquiry = adminInquiryService.findInquiryByCode(inquiryCode);
        ReplyDTO reply = adminInquiryService.findReplyByCode(inquiryCode);

        mv.addObject("inquiry", inquiry);
        mv.addObject("reply", reply);
        mv.addObject("activeSection", "inquiryDetail");
        mv.setViewName("admin/layout/adminLayout");

        return mv;
    }

    @PostMapping("/delete/{inquiryCode}")
    public String deleteInquiry(@PathVariable("inquiryCode") int inquiryCode,
                                RedirectAttributes rAttr) {

        adminInquiryService.deleteInquiry(inquiryCode);

        rAttr.addFlashAttribute("message", "문의글이 삭제되었습니다.");

        return "redirect:/admin/inquiry/list";
    }
}
