package com.space.spacesinspace.admin.reply.controller;

import com.space.spacesinspace.admin.reply.model.service.ReplyService;
import com.space.spacesinspace.common.dto.ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/inquiry/detail")
public class ReplyController {

    private final ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @GetMapping("regist")
    public void registPage() {}

    @PostMapping("/admin/inquiry/detail")
    public String registReply(ReplyDTO newReply, RedirectAttributes rAttr) {
        replyService.registReply(newReply);

        rAttr.addFlashAttribute("successMessage", "답변이 등록되었습니다.");

        return "redirect:/admin/inquiry/list";
    }

    @PostMapping("/delete/{replyCode}")
    public String deleteReply(@PathVariable("replyCode") int replyCode,
                              RedirectAttributes rAttr) {

        replyService.deleteReply(replyCode);

        rAttr.addFlashAttribute("successMessage", "문의글이 삭제되었습니다.");

        return "redirect:/admin/inquiry/list";
    }

    @GetMapping("edit/{replyCode}")
    public String updateForm(@PathVariable("replyCode") int replyCode, Model model) {

        ReplyDTO reply = replyService.findReplyByCode(replyCode);

        model.addAttribute("reply", reply);

        return "admin/inquiry/replyEdit";
    }

    @PostMapping("update")
    public String updateReply(@ModelAttribute ReplyDTO reply, RedirectAttributes rAttr) throws Exception {

        replyService.updateReply(reply);

        rAttr.addFlashAttribute("successMessage", "문의글이 수정되었습니다.");

        return "redirect:/admin/inquiry/list";
    }
}
