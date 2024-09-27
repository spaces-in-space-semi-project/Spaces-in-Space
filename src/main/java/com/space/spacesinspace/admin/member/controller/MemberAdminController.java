package com.space.spacesinspace.admin.member.controller;

import com.space.spacesinspace.admin.member.model.service.MemberAdminService;
import com.space.spacesinspace.common.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
        System.out.println(memberList);
        mv.addObject("memberList", memberList);
        mv.addObject("activeSection", "member");
        mv.setViewName("admin/layout/adminLayout");
        return mv;
    }
}
