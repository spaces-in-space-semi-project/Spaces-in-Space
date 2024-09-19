package com.space.spacesinspace.common.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

public class FaqDTO {

    private int faqCode;
    private String faqTitle;
    private String faqDetail;

    public FaqDTO() {
    }

    public FaqDTO(int faqCode, String faqTitle, String faqDetail) {
        this.faqCode = faqCode;
        this.faqTitle = faqTitle;
        this.faqDetail = faqDetail;
    }

    public int getFaqCode() {
        return faqCode;
    }

    public void setFaqCode(int faqCode) {
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
