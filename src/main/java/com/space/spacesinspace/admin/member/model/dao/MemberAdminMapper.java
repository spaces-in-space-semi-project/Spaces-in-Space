package com.space.spacesinspace.admin.member.model.dao;

import com.space.spacesinspace.common.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberAdminMapper {

    List<MemberDTO> selectAllMembers();
}
