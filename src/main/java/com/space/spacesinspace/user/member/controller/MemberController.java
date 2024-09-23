package com.space.spacesinspace.user.member.controller;

import com.space.spacesinspace.common.dto.MemberDTO;
import com.space.spacesinspace.user.member.model.dto.SignupDTO;
import com.space.spacesinspace.user.member.model.dto.UpdateMemberDTO;
import com.space.spacesinspace.user.member.model.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user/member/*")
public class MemberController {

    private MemberService memberService;
//    private SignupDTO signupDTO;

    @Autowired
    public MemberController(MemberService memberService/*, com.space.spacesinspace.user.member.model.dto.SignupDTO signupDTO*/) {
        this.memberService = memberService;
//        this.signupDTO = signupDTO;
    }

    @GetMapping("signup")
    public String signup() {
        return "user/member/signup"; // Path to your Thymeleaf template
    }

    @PostMapping("/checkDuplicateId")
    @ResponseBody
    public boolean checkDuplicateId(@RequestParam String memberId) {
       return memberService.checkDuplicateId(memberId);
    }

    @PostMapping("signup")
    public ModelAndView signup(ModelAndView mv, @ModelAttribute SignupDTO newMember) {
        Integer result = memberService.regist(newMember);
        String message = null;

        if (result == null) {
            message = "이미 해당 정보로 가입된 회원이 존재합니다.";
        } else if (result == 0) {
            message = "회원가입에 실패했습니다. 다시 시도해주세요.";
        } else if (result >= 1) {
            message = "회원가입이 성공적으로 완료되었습니다.";
            mv.setViewName("auth/login"); // 가입 성공 시 로그인 페이지로 리다이렉트
            mv.addObject("message", message);
            return mv; // 여기서 return
        } else {
            message = "알 수 없는 오류가 발생했습니다. 다시 시도해보시거나 관리자에게 문의해주세요.";
        }

        // 오류 메시지를 설정하고 다시 폼으로 돌아가기
        mv.addObject("message", message);
        mv.setViewName("user/member/signup");

        return mv;
    }

    @GetMapping("update")
    public ModelAndView update(ModelAndView mv, @AuthenticationPrincipal MemberDTO member) {
        String memberId = member.getUsername();

        MemberDTO memberInfo = memberService.findByMemberId(memberId);

        mv.addObject("memberName", "회원");
        mv.addObject("memberInfo", memberInfo);
        mv.addObject("activeSection", "update");
        mv.setViewName("user/member/myPage");

        return mv;
    }

    @PostMapping("update")
    public ModelAndView updateInfo(ModelAndView mv,
                                   @AuthenticationPrincipal MemberDTO member,
                                   @ModelAttribute UpdateMemberDTO memberUpdateInfo) {
        Integer result = memberService.updateInfo(memberUpdateInfo);

        String memberId = member.getUsername();
        MemberDTO memberInfo = memberService.findByMemberId(memberId);

        String message = null;

        if (result == null) {
            message = "정보 수정에 실패했습니다. 다시 시도해주세요.";
            System.out.println(message);

            mv.addObject("memberName", "회원");
            mv.addObject("memberInfo", memberInfo);
            mv.addObject("activeSection", "update");
            mv.addObject("message", message);
            mv.setViewName("user/member/myPage");
        } else if (result == 0) {
            message = "정보 수정에 실패했습니다. 다시 시도해주세요.";
            System.out.println(message);

            mv.addObject("memberName", "회원");
            mv.addObject("memberInfo", memberInfo);
            mv.addObject("activeSection", "update");
            mv.addObject("message", message);
            mv.setViewName("user/member/myPage");
        } else if (result >= 1) {
            message = "정보 수정이 성공적으로 완료되었습니다.";
            System.out.println(message);

            mv.addObject("memberName", "회원");
            mv.addObject("memberInfo", memberInfo);
            mv.addObject("activeSection", "update");
            mv.addObject("message", message);
            mv.setViewName("user/member/myPage");
        } else {
            message = "알 수 없는 오류가 발생했습니다. 다시 시도해보시거나 관리자에게 문의해주세요..";
            System.out.println(message);

            mv.addObject("memberName", "회원");
            mv.addObject("memberInfo", memberInfo);
            mv.addObject("activeSection", "update");
            mv.addObject("message", message);
            mv.setViewName("user/member/myPage");
        }

        return mv;
    }
}
