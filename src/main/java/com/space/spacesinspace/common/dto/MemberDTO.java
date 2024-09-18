package com.space.spacesinspace.common.dto;

import com.space.spacesinspace.auth.model.MemberRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class MemberDTO implements UserDetails {

    private int memberCode;
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private String memberAddress;
    private MemberRole memberRole;
    private String memberDeleteYn;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> memberRole.getRole());

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.memberPw;
    }

    @Override
    public String getUsername() {
        return this.memberId;
    }

    public MemberDTO() {
    }

    public MemberDTO(int memberCode, String memberId, String memberPw, String memberName, String memberEmail, String memberPhone, String memberAddress, MemberRole memberRole, String memberDeleteYn) {
        this.memberCode = memberCode;
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
        this.memberAddress = memberAddress;
        this.memberRole = memberRole;
        this.memberDeleteYn = memberDeleteYn;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPw() {
        return memberPw;
    }

    public void setMemberPw(String memberPw) {
        this.memberPw = memberPw;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public MemberRole getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(MemberRole memberRole) {
        this.memberRole = memberRole;
    }

    public String getMemberDeleteYn() {
        return memberDeleteYn;
    }

    public void setMemberDeleteYn(String memberDeleteYn) {
        this.memberDeleteYn = memberDeleteYn;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "memberCode=" + memberCode +
                ", memberId='" + memberId + '\'' +
                ", memberPw='" + memberPw + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberPhone='" + memberPhone + '\'' +
                ", memberAddress='" + memberAddress + '\'' +
                ", memberRole='" + memberRole + '\'' +
                ", memberDeleteYn='" + memberDeleteYn + '\'' +
                '}';
    }
}
