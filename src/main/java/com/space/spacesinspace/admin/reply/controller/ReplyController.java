package com.space.spacesinspace.admin.reply.controller;

import com.space.spacesinspace.admin.reply.model.service.ReplyService;
import com.space.spacesinspace.common.dto.InquiryDTO;
import com.space.spacesinspace.common.dto.ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/admin/inquiry/*")
public class ReplyController {

    private final ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @GetMapping("regist")
    public String registPage(Model model) {
        model.addAttribute("replyCode", "답변코드");
        model.addAttribute("activeSection", "registReply");

        return "admin/inquiry/detail";
    }

    @PostMapping("registReply")
    public String registReply(ReplyDTO newReply, RedirectAttributes rAttr, @AuthenticationPrincipal InquiryDTO inquiry) {

        int inquiryCode = inquiry.getInquiryCode();
        newReply.setInquiryCode(inquiryCode);

        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        newReply.setReplyDate(formattedDate);

        replyService.registNewReply(newReply);

        rAttr.addFlashAttribute("successMessage", "신규 답변이 등록되었습니다.");

        return "redirect:/admin/inquiry/list";
    }

    @PostMapping("/deleteReply/{replyCode}")
    public String deleteReply(@PathVariable("replyCode") int replyCode,
                              RedirectAttributes rAttr) {

        replyService.deleteReply(replyCode);

        rAttr.addFlashAttribute("successMessage", "문의글이 삭제되었습니다.");

        return "redirect:/admin/inquiry/list";
    }

    @PostMapping("update")
    public String updateReply(@ModelAttribute ReplyDTO reply, RedirectAttributes rAttr) {
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        reply.setReplyEditDate(formattedDate);

        Integer result = replyService.updateReply(reply);

        String message;
        if (result == null || result == 0) {
            message = "답변 수정에 실패했습니다. 다시 시도해주세요.";
        } else if (result >= 1) {
            message = "답변 수정이 성공적으로 완료되었습니다.";
        } else {
            message = "알 수 없는 오류가 발생했습니다. 다시 시도해주세요.";
        }

        rAttr.addFlashAttribute("message", message);

        return "redirect:/admin/inquiry/list";
    }
}
