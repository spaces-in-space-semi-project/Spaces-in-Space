package com.space.spacesinspace.user.member.model.dao;

import com.space.spacesinspace.common.dto.MemberDTO;
import com.space.spacesinspace.user.member.model.dto.SignupDTO;
import com.space.spacesinspace.user.member.model.dto.UpdateMemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface MemberMapper {

    @Transactional
    int regist(SignupDTO newMember);

    MemberDTO findByMemberId(String memberId);

    @Transactional
    Integer updateInfo(UpdateMemberDTO memberInfo);

    int checkDuplicateId(String memberId);

    String findIdByEmail(String email);
}
