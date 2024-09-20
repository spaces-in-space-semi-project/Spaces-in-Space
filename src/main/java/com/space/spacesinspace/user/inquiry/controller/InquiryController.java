package com.space.spacesinspace.user.inquiry.controller;

import com.space.spacesinspace.common.dto.InquiryDTO;
import com.space.spacesinspace.user.inquiry.model.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user/inquiry/*")
public class InquiryController {

    private final InquiryService inquiryService;
    private Object memberCode;

    @Autowired
    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("/list")
    public String findAllInquiry(Model model) {

        List<InquiryDTO> inquiryList = inquiryService.findAllInquiry(memberCode);

        model.addAttribute("inquiryList", inquiryList);
        model.addAttribute("memberName", "회원");
        model.addAttribute("activeSection", "inquiry");

        return "user/member/myPage";
    }

    @GetMapping("detail/{code}")
    public String findInquiryByCode(@PathVariable("code") int code,
                                    Model model) {

        InquiryDTO inquiry = inquiryService.findInquiryByCode(code);

        model.addAttribute("inquiry", inquiry);

        return "user/inquiry/detail";
    }
}
