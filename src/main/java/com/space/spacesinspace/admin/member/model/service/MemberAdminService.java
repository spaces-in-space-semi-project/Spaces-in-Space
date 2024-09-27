package com.space.spacesinspace.admin.member.model.service;

import com.space.spacesinspace.admin.member.model.dao.MemberAdminMapper;
import com.space.spacesinspace.common.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberAdminService {

    private final MemberAdminMapper memberAdminMapper;

    @Autowired
    public MemberAdminService(MemberAdminMapper memberAdminMapper) {
        this.memberAdminMapper = memberAdminMapper;
    }

    public List<MemberDTO> selectAllMembers() {
        return memberAdminMapper.selectAllMembers();
    }
}
