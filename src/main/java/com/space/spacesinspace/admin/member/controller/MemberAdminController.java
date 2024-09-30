package com.space.spacesinspace.admin.member.controller;

import com.space.spacesinspace.admin.member.model.service.MemberAdminService;
import com.space.spacesinspace.common.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("admin/member/*")
public class MemberAdminController {

    private final MemberAdminService memberAdminService;

    @Autowired
    public MemberAdminController(MemberAdminService memberAdminService) {
        this.memberAdminService = memberAdminService;
    }

    @GetMapping("memberList")
    public ModelAndView selectAllMembers(ModelAndView mv) {
        List<MemberDTO> memberList = memberAdminService.selectAllMembers();
        mv.addObject("memberList", memberList);
        mv.addObject("activeSection", "member");
        mv.setViewName("admin/layout/adminLayout");
        return mv;
    }

    @GetMapping("searchMember")
    public ModelAndView searchMember(ModelAndView mv, @RequestParam String searchValue) {
        List<MemberDTO> searchMemberList = memberAdminService.searchMember(searchValue);
        mv.addObject("memberList", searchMemberList);
        mv.addObject("activeSection", "member");
        mv.setViewName("admin/layout/adminLayout");
        return mv;
    }

    @GetMapping("memberDetail/{memberCode}")
    public ModelAndView findMemberDetail(@PathVariable("memberCode") int memberCode, ModelAndView mv) {
        MemberDTO memberInfo = memberAdminService.findMemberDetail(memberCode);
        mv.addObject("memberInfo", memberInfo);
        mv.addObject("activeSection", "memberDetail");
        mv.setViewName("admin/layout/adminLayout");
        return mv;
    }

    @PostMapping("update")
    public ModelAndView updateMember(@ModelAttribute MemberDTO member, ModelAndView mv, RedirectAttributes rAttr) {
        Integer result = memberAdminService.updateMember(member);

        String message;
        if (result == null || result == 0) {
            message = "회원정보 수정에 실패했습니다. 다시 시도해주세요.";
            mv.setViewName("redirect:/admin/member/memberDetail/" + member.getMemberCode());
        } else {
            message = "회원정보 수정을 성공적으로 완료했습니다.";
            mv.setViewName("redirect:/admin/member/memberList");
        }

        rAttr.addFlashAttribute("message", message);

        return mv;
    }

    @PostMapping("memberDelete")
    public ModelAndView deleteMember(@RequestParam int memberCode, ModelAndView mv, RedirectAttributes rAttr) {
        Integer result =  memberAdminService.deleteMember(memberCode);

        String message;

        if (result == null || result == 0) {
            message = "회원을 탈퇴 시키는 데 실패했습니다. 다시 시도해주세요.";
            mv.setViewName("redirect:/admin/member/update");
        } else {
            message = "회원이 탈퇴되었습니다.";
            mv.setViewName("redirect:/admin/member/memberList");
        }

        rAttr.addFlashAttribute("message", message);

        return mv;
    }
}
