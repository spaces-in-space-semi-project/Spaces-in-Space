package com.space.spacesinspace.common.dto;

public class ReplyDTO {

    private int replyCode;
    private int memberCode;
    private int inquiryCode;
    private String replyDetail;
    private String replyDate;
    private String replyEditDate;

    public ReplyDTO() {
    }

    public ReplyDTO(int replyCode, int memberCode, int inquiryCode, String replyDetail, String replyDate, String replyEditDate) {
        this.replyCode = replyCode;
        this.memberCode = memberCode;
        this.inquiryCode = inquiryCode;
        this.replyDetail = replyDetail;
        this.replyDate = replyDate;
        this.replyEditDate = replyEditDate;
    }

    public int getReplyCode() {
        return replyCode;
    }

    public void setReplyCode(int replyCode) {
        this.replyCode = replyCode;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }

    public int getInquiryCode() {
        return inquiryCode;
    }

    public void setInquiryCode(int inquiryCode) {
        this.inquiryCode = inquiryCode;
    }

    public String getReplyDetail() {
        return replyDetail;
    }

    public void setReplyDetail(String replyDetail) {
        this.replyDetail = replyDetail;
    }

    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

    public String getReplyEditDate() {
        return replyEditDate;
    }

    public void setReplyEditDate(String replyEditDate) {
        this.replyEditDate = replyEditDate;
    }

    @Override
    public String toString() {
        return "ReplyDTO{" +
                "replyCode=" + replyCode +
                ", memberCode=" + memberCode +
                ", inquiryCode=" + inquiryCode +
                ", replyDetail='" + replyDetail + '\'' +
                ", replyDate='" + replyDate + '\'' +
                ", replyEditDate='" + replyEditDate + '\'' +
                '}';
    }
}
