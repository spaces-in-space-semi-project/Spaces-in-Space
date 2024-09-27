package com.space.spacesinspace.admin.inquiry.controller;

import com.space.spacesinspace.admin.inquiry.model.service.AdminInquiryService;
import com.space.spacesinspace.admin.reply.model.service.ReplyService;
import com.space.spacesinspace.common.dto.InquiryDTO;
import com.space.spacesinspace.common.dto.ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/inquiry/*")
public class AdminInquiryController {

    private final AdminInquiryService adminInquiryService;
    private final ReplyService replyService;

    @Autowired
    public AdminInquiryController(AdminInquiryService adminInquiryService, ReplyService replyService) {
        this.adminInquiryService = adminInquiryService;
        this.replyService = replyService;
    }

    @GetMapping("/list")
    public ModelAndView findAllInquiry(ModelAndView mv) {

        List<InquiryDTO> inquiryList = adminInquiryService.findAllInquiry();

        mv.addObject("inquiryList", inquiryList);
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
