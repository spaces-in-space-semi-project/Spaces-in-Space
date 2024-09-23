package com.space.spacesinspace.user.inquiry.controller;

import com.space.spacesinspace.common.dto.InquiryDTO;
import com.space.spacesinspace.common.dto.MemberDTO;
import com.space.spacesinspace.user.inquiry.model.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String findAllInquiry(Model model, @AuthenticationPrincipal MemberDTO member) {

        int memberCode = member.getMemberCode();

        List<InquiryDTO> inquiryList = inquiryService.findAllInquiryBy(memberCode);

        model.addAttribute("inquiryList", inquiryList);
        model.addAttribute("memberName", "회원");
        model.addAttribute("activeSection", "inquiry");

        return "user/member/myPage";
    }

    @GetMapping("/detail/{code}")
    public String findInquiryByCode(@PathVariable("code") int code,
                                    Model model) {

        InquiryDTO inquiry = inquiryService.findInquiryByCode(code);

        model.addAttribute("inquiry", inquiry);

        return "user/inquiry/detail";
    }

    @GetMapping("regist")
    public void registPage() {}

    @PostMapping("regist")
    public String registInquiry(InquiryDTO newInquiry, RedirectAttributes rAttr) {
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

    @PostMapping("/update")
    public String updateInquiry(InquiryDTO inquiry, RedirectAttributes rAttr) {

        inquiryService.updateInquiry(inquiry);

        rAttr.addFlashAttribute("successMessage", "문의글 수정이 완료되었습니다.");

        return "redirect:/user/inquiry/detail/" + inquiry.getInquiryCode();
    }
}
