package com.space.spacesinspace.common.dto;

public class InquiryDTO {

    private int inquiryCode;
    private int memberCode;
    private String inquiryTitle;
    private String inquiryDetail;
    private String inquiryDate;
    private String inquiryEditDate;

    public InquiryDTO() {
    }

    public InquiryDTO(int inquiryCode, int memberCode, String inquiryTitle, String inquiryDetail, String inquiryDate, String inquiryEditDate) {
        this.inquiryCode = inquiryCode;
        this.memberCode = memberCode;
        this.inquiryTitle = inquiryTitle;
        this.inquiryDetail = inquiryDetail;
        this.inquiryDate = inquiryDate;
        this.inquiryEditDate = inquiryEditDate;
    }


    public int getInquiryCode() {
        return inquiryCode;
    }

    public void setInquiryCode(int inquiryCode) {
        this.inquiryCode = inquiryCode;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }

    public String getInquiryTitle() {
        return inquiryTitle;
    }

    public void setInquiryTitle(String inquiryTitle) {
        this.inquiryTitle = inquiryTitle;
    }

    public String getInquiryDetail() {
        return inquiryDetail;
    }

    public void setInquiryDetail(String inquiryDetail) {
        this.inquiryDetail = inquiryDetail;
    }

    public String getInquiryDate() {
        return inquiryDate;
    }

    public void setInquiryDate(String inquiryDate) {
        this.inquiryDate = inquiryDate;
    }

    public String getInquiryEditDate() {
        return inquiryEditDate;
    }

    public void setInquiryEditDate(String inquiryEditDate) {
        this.inquiryEditDate = inquiryEditDate;
    }

    @Override
    public String toString() {
        return "InquiryDTO{" +
                "inquiryCode=" + inquiryCode +
                ", memberCode=" + memberCode +
                ", inquiryTitle='" + inquiryTitle + '\'' +
                ", inquiryDetail='" + inquiryDetail + '\'' +
                ", inquiryDate='" + inquiryDate + '\'' +
                ", inquiryEditDate='" + inquiryEditDate + '\'' +
                '}';
    }

}
