package com.space.spacesinspace.user.inquiry.controller;

import com.space.spacesinspace.common.dto.InquiryDTO;
import com.space.spacesinspace.common.dto.MemberDTO;
import com.space.spacesinspace.common.dto.ReplyDTO;
import com.space.spacesinspace.user.inquiry.model.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        ReplyDTO reply = inquiryService.findReplyByCode(inquiryCode);

        model.addAttribute("inquiry", inquiry);
        model.addAttribute("reply", reply);
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
    public ModelAndView registInquiry(ModelAndView mv, @ModelAttribute InquiryDTO newInquiry, RedirectAttributes rAttr, @AuthenticationPrincipal MemberDTO member) {
        int memberCode = member.getMemberCode();
        newInquiry.setMemberCode(memberCode);

        LocalDateTime currentDateTime = LocalDateTime.now();
        String formattedDateTime = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        newInquiry.setInquiryDate(formattedDateTime);

        Integer result =  inquiryService.registNewInquiry(newInquiry);

        String message;
        if (result == null || result == 0) {
            message = "문의글 등록에 실패했습니다. 다시 시도해주세요.";
        } else {
            message = "문의글을 성공적으로 등록했습니다.";
        }

        rAttr.addFlashAttribute("message", message);
        mv.setViewName("redirect:/user/inquiry/list");

        return mv;
    }

    @PostMapping("/delete/{code}")
    public ModelAndView deleteInquiry(@PathVariable("code") int code,
                                      RedirectAttributes rAttr, ModelAndView mv) {

        Integer result = inquiryService.deleteInquiry(code);

        String message;
        if (result == null || result == 0) {
            message = "문의글 삭제에 실패했습니다. 다시 시도해주세요.";
        } else {
            message = "문의글을 성공적으로 삭제했습니다.";
        }

        rAttr.addFlashAttribute("message", message);
        mv.setViewName("redirect:/user/inquiry/list");

        return mv;
    }

//    @GetMapping("edit/{inquiryCode}")
//    public String updateForm(@PathVariable("inquiryCode") int inquiryCode, Model model) {
//
//        InquiryDTO inquiry = inquiryService.findInquiryByCode(inquiryCode);
//
//        model.addAttribute("inquiry", inquiry);
//        model.addAttribute("memberName", "회원");
//        model.addAttribute("activeSection", "inquiryEdit");
//
//        return "user/member/myPage";
//    }

    @PostMapping("update")
    public String updateInquiry(@ModelAttribute InquiryDTO inquiry, RedirectAttributes rAttr) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        String formattedDateTime = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        inquiry.setInquiryEditDate(formattedDateTime);

        Integer result = inquiryService.updateInquiry(inquiry);

        String message;
        if (result == null || result == 0) {
            message = "문의글 수정에 실패했습니다. 다시 시도해주세요.";
        } else if (result >= 1) {
            message = "문의글 수정이 성공적으로 완료되었습니다.";
        } else {
            message = "알 수 없는 오류가 발생했습니다. 다시 시도해보시거나 관리자에게 문의해주세요.";
        }

        rAttr.addFlashAttribute("message", message);

        return "redirect:/user/inquiry/list";
    }
}
