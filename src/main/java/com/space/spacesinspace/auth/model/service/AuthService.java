package com.space.spacesinspace.auth.model.service;

import com.space.spacesinspace.common.dto.MemberDTO;
import com.space.spacesinspace.user.member.model.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService implements UserDetailsService {

    private final MemberService memberService;

    @Autowired
    public AuthService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        MemberDTO member = memberService.findByMemberId(memberId);

        if (Objects.isNull(member)) throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");

        return member;
    }
}
