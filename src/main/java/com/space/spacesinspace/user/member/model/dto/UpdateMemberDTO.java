package com.space.spacesinspace.user.member.model.dto;

public class UpdateMemberDTO {

    private String memberId;
    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private String memberAddress;

    public UpdateMemberDTO() {
    }

    public UpdateMemberDTO(String memberId, String memberName, String memberEmail, String memberPhone, String memberAddress) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
        this.memberAddress = memberAddress;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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

    @Override
    public String toString() {
        return "UpdateMemberDTO{" +
                "memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberPhone='" + memberPhone + '\'' +
                ", memberAddress='" + memberAddress + '\'' +
                '}';
    }
}
