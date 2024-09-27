package com.space.spacesinspace.admin.inquiry.controller;

import com.space.spacesinspace.admin.inquiry.model.service.AdminInquiryService;
import com.space.spacesinspace.admin.reply.model.service.ReplyService;
import com.space.spacesinspace.common.dto.InquiryDTO;
import com.space.spacesinspace.common.dto.ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String findAllInquiry(Model model) {

        List<InquiryDTO> inquiryList = adminInquiryService.findAllInquiry();

        model.addAttribute("inquiryList", inquiryList);

        return "admin/inquiry/list";
    }

    @GetMapping("/detail/{inquiryCode}")
    public String findInquiryByCode(@PathVariable("inquiryCode") int inquiryCode, ReplyDTO reply,
                                    Model model) {

        InquiryDTO inquiry = adminInquiryService.findInquiryByCode(inquiryCode);

        ReplyDTO replyDTO = replyService.findReplyByCode(inquiryCode);

        model.addAttribute("inquiry", inquiry);

        return "admin/inquiry/detail";
    }

    @PostMapping("/delete/{inquiryCode}")
    public String deleteInquiry(@PathVariable("inquiryCode") int inquiryCode) {

        adminInquiryService.deleteInquiry(inquiryCode);

        return "redirect:/admin/inquiry/list";
    }
}
