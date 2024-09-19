package com.space.spacesinspace.user.member.controller;

import com.space.spacesinspace.user.member.model.dto.SignupDTO;
import com.space.spacesinspace.user.member.model.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user/member/*")
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("signup")
    public String signup() {
        return "/user/member/signup";
    }

    @PostMapping("signup")
    public ModelAndView signup(ModelAndView mv, @ModelAttribute SignupDTO newMember) {
        Integer result = memberService.regist(newMember);

        String message = null;

        if (result == null) {
            message = "이미 해당 정보로 가입된 회원이 존재합니다.";
            System.out.println(message);

            mv.setViewName("member/signup");
        } else if (result == 0) {
            message = "회원가입에 실패했습니다. 다시 시도해주세요.";
            System.out.println(message);

            mv.setViewName("member/signup");
        } else if (result >= 1) {
            message = "회원가입이 성공적으로 완료되었습니다.";
            System.out.println(message);

            mv.setViewName("auth/login");
        } else {
            message = "알 수 없는 오류가 발생했습니다. 다시 시도해보시거나 관리자에게 문의해주세요.";
            System.out.println(message);

            mv.setViewName("member/signup");
        }

        return mv;
    }
}
