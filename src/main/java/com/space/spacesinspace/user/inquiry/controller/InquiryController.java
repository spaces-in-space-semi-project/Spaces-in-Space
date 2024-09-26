package com.space.spacesinspace.user.inquiry.controller;

import com.space.spacesinspace.common.dto.InquiryDTO;
import com.space.spacesinspace.common.dto.MemberDTO;
import com.space.spacesinspace.user.inquiry.model.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/user/inquiry/*")
public class InquiryController {

    private final InquiryService inquiryService;

    @Autowired
    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("/list")
    public String findAllInquiry(Model model, @AuthenticationPrincipal MemberDTO member) {

        int memberCode = member.getMemberCode();

        List<InquiryDTO> inquiryList = inquiryService.findAllInquiryBy(memberCode);

        model.addAttribute("inquiryList", inquiryList);
        model.addAttribute("memberName", "회원");
        model.addAttribute("activeSection", "inquiry");

        return "user/member/myPage";
    }

    @GetMapping("/detail/{inquiryCode}")
    public String findInquiryByCode(@PathVariable("inquiryCode") int inquiryCode,
                                    Model model) {

        InquiryDTO inquiry = inquiryService.findInquiryByCode(inquiryCode);

        model.addAttribute("inquiry", inquiry);
        model.addAttribute("memberName", "회원");
        model.addAttribute("activeSection", "inquiryDetail");

        return "user/member/myPage";
    }

    @GetMapping("regist")
    public String registPage(Model model) {
        model.addAttribute("memberName", "회원");
        model.addAttribute("activeSection", "inquiryRegist");

        return "user/member/myPage";
    }

    @PostMapping("/user/inquiry/regist")
    public String registInquiry(InquiryDTO newInquiry, RedirectAttributes rAttr, @AuthenticationPrincipal MemberDTO member) {
        int memberCode = member.getMemberCode();
        newInquiry.setMemberCode(memberCode);

        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        newInquiry.setInquiryDate(formattedDate);

        inquiryService.registNewInquiry(newInquiry);

        rAttr.addFlashAttribute("successMessage", "신규 문의글이 등록되었습니다.");

        return "redirect:/user/inquiry/list";
    }

    @PostMapping("/delete/{code}")
    public String deleteInquiry(@PathVariable("code") int code,
                                RedirectAttributes rAttr) {

        inquiryService.deleteInquiry(code);

        rAttr.addFlashAttribute("successMessage", "문의글이 삭제되었습니다.");

        return "redirect:/user/inquiry/list";
    }

    @GetMapping("edit/{inquiryCode}")
    public String updateForm(@PathVariable("inquiryCode") int inquiryCode, Model model) {

        InquiryDTO inquiry = inquiryService.findInquiryByCode(inquiryCode);

        model.addAttribute("inquiry", inquiry);
        model.addAttribute("memberName", "회원");
        model.addAttribute("activeSection", "inquiryEdit");

        return "user/member/myPage";
    }

    @PostMapping("update")
    public String updateInquiry(@ModelAttribute InquiryDTO inquiry, RedirectAttributes rAttr) throws Exception {

        inquiryService.updateInquiry(inquiry);

        rAttr.addFlashAttribute("successMessage", "문의글이 수정되었습니다.");

        return "redirect:/user/inquiry/list";
    }
}
