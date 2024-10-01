package com.space.spacesinspace.admin.reply.controller;

import com.space.spacesinspace.admin.reply.model.service.ReplyService;
import com.space.spacesinspace.common.dto.InquiryDTO;
import com.space.spacesinspace.common.dto.MemberDTO;
import com.space.spacesinspace.common.dto.ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/admin/reply/*")
public class ReplyController {

    private final ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/registReply/{inquiryCode}")
    public String registReply(RedirectAttributes rAttr, @PathVariable int inquiryCode, @RequestParam String replyDetail2, @AuthenticationPrincipal MemberDTO member) {

        ReplyDTO newReply = new ReplyDTO();
        newReply.setInquiryCode(inquiryCode);

        newReply.setReplyDetail(replyDetail2);

        int memberCode = member.getMemberCode();
        newReply.setMemberCode(memberCode);

        LocalDateTime currentDateTime = LocalDateTime.now();
        String formattedDateTime = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        newReply.setReplyDate(formattedDateTime);

        Integer result = replyService.registNewReply(newReply);

        String message;
        if (result == null || result == 0) {
            message = "답변 등록에 실패했습니다. 다시 시도해주세요.";
        } else if (result >= 1) {
            message = "답변 등록이 성공적으로 완료되었습니다.";
        } else {
            message = "알 수 없는 오류가 발생했습니다. 다시 시도해주세요.";
        }

        rAttr.addFlashAttribute("message", message);

        return "redirect:/admin/inquiry/detail/" + inquiryCode;
    }

    @PostMapping("/deleteReply")
    public String deleteReply(@RequestParam int replyCode,
                              RedirectAttributes rAttr) {

        replyService.deleteReply(replyCode);

        rAttr.addFlashAttribute("successMessage", "문의글이 삭제되었습니다.");

        return "redirect:/admin/inquiry/list";
    }

    @PostMapping("/updateReply")
    public String updateReply(@ModelAttribute ReplyDTO reply, RedirectAttributes rAttr) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        String formattedDateTime = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        reply.setReplyEditDate(formattedDateTime);

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
