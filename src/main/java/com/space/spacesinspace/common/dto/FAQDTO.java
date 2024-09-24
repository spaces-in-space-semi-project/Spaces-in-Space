package com.space.spacesinspace.common.dto;

public class FAQDTO {

    private String faqCode;
    private String faqTitle;
    private String faqDetail;

    public FAQDTO() {
    }

    public FAQDTO(String faqCode, String faqTitle, String faqDetail) {
        this.faqCode = faqCode;
        this.faqTitle = faqTitle;
        this.faqDetail = faqDetail;
    }

    public String getFaqCode() {
        return faqCode;
    }

    public void setFaqCode(String faqCode) {
        this.faqCode = faqCode;
    }

    public String getFaqTitle() {
        return faqTitle;
    }

    public void setFaqTitle(String faqTitle) {
        this.faqTitle = faqTitle;
    }

    public String getFaqDetail() {
        return faqDetail;
    }

    public void setFaqDetail(String faqDetail) {
        this.faqDetail = faqDetail;
    }

    @Override
    public String toString() {
        return "FaqDTO{" +
                "faqCode=" + faqCode +
                ", faqTitle='" + faqTitle + '\'' +
                ", faqDetail='" + faqDetail + '\'' +
                '}';
    }
}
