package com.space.spacesinspace.user.member.model.dao;

import com.space.spacesinspace.common.dto.MemberDTO;
import com.space.spacesinspace.user.member.model.dto.SignupDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    int regist(SignupDTO newMember);

    MemberDTO findByMemberId(String memberId);
}
