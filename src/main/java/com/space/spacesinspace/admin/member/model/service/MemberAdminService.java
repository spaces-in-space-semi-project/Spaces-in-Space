package com.space.spacesinspace.admin.member.model.service;

import com.space.spacesinspace.admin.member.model.dao.MemberAdminMapper;
import com.space.spacesinspace.common.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public MemberDTO findMemberDetail(int memberCode) {
        return memberAdminMapper.findMemberDetail(memberCode);
    }

    @Transactional
    public Integer updateMember(MemberDTO member) {
        return memberAdminMapper.updateMember(member);
    }

    @Transactional
    public Integer deleteMember(int memberCode) {
        return memberAdminMapper.deleteMember(memberCode);
    }

    public List<MemberDTO> searchMember(String searchValue) {
        return memberAdminMapper.searchMember(searchValue);
    }
}
