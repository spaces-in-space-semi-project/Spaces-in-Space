package com.space.spacesinspace.user.member.model.service;

import com.space.spacesinspace.user.member.model.dao.MemberMapper;
import com.space.spacesinspace.common.dto.MemberDTO;
import com.space.spacesinspace.user.member.model.dto.SignupDTO;
import com.space.spacesinspace.user.member.model.dto.UpdateMemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MemberService {

    private PasswordEncoder encoder;
    private MemberMapper memberMapper;

    @Autowired
    public MemberService(PasswordEncoder encoder, MemberMapper memberMapper) {
        this.encoder = encoder;
        this.memberMapper = memberMapper;
    }

    public Integer regist(SignupDTO newMember) {
        newMember.setMemberPw(encoder.encode(newMember.getMemberPw()));

        Integer result = null;

        try {
            result = memberMapper.regist(newMember);
        } catch (DuplicateKeyException e) {
            result = 0;
            e.printStackTrace();
        } catch (BadSqlGrammarException e) {
            result = 0;
            e.printStackTrace();
        }

        return result;
    }

    public MemberDTO findByMemberId(String memberId) {
        MemberDTO member = memberMapper.findByMemberId(memberId);

        if (!Objects.isNull(member)) {
            return member;
        } else {
            return null;
        }
    }

    public Integer updateInfo(UpdateMemberDTO memberInfo) {
        Integer result = null;

        try {
            result = memberMapper.updateInfo(memberInfo);
        } catch (BadSqlGrammarException e) {
            result = 0;
            e.printStackTrace();
        }

        return result;
    }

    public boolean checkDuplicateId(String memberId) {
        return memberMapper.checkDuplicateId(memberId) > 0;
    }
}
