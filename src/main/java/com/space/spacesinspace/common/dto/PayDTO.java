package com.space.spacesinspace.common.dto;

import java.util.Date;

public class PayDTO {

    private int payCode;                // 결제코드
    private int memberCode;             // 회원코드
    private String payDate;             // 결제일자
    private int payTotalCnt;            // 총 결제수량
    private int payTotalPrice;          // 총 결제금액
    private String payAddress;          // 배송주소
    private String payReceiver;         // 받는분 성함
    private String payDeliverPhone;     // 받는분 연락처
    private String payDeliverStatus;    // 배송 상태
    private String payRefundYn;         // 결제결제상태
    private Long payAccountNumber;       // 계좌번호
    private Long payCardNumber;          // 카드번호
    private int bankCode;               // 은행코드
    private int cardCompanyCode;        // 카드사코드

    public PayDTO() {
    }

    public PayDTO(int bankCode, int cardCompanyCode, int memberCode, Long payAccountNumber, String payAddress, Long payCardNumber, int payCode, String payDate, String payDeliverPhone, String payDeliverStatus, String payReceiver, String payRefundYn, int payTotalCnt, int payTotalPrice) {
        this.bankCode = bankCode;
        this.cardCompanyCode = cardCompanyCode;
        this.memberCode = memberCode;
        this.payAccountNumber = payAccountNumber;
        this.payAddress = payAddress;
        this.payCardNumber = payCardNumber;
        this.payCode = payCode;
        this.payDate = payDate;
        this.payDeliverPhone = payDeliverPhone;
        this.payDeliverStatus = payDeliverStatus;
        this.payReceiver = payReceiver;
        this.payRefundYn = payRefundYn;
        this.payTotalCnt = payTotalCnt;
        this.payTotalPrice = payTotalPrice;
    }

    public int getBankCode() {
        return bankCode;
    }

    public void setBankCode(int bankCode) {
        this.bankCode = bankCode;
    }

    public int getCardCompanyCode() {
        return cardCompanyCode;
    }

    public void setCardCompanyCode(int cardCompanyCode) {
        this.cardCompanyCode = cardCompanyCode;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }

    public Long getPayAccountNumber() {
        return payAccountNumber;
    }

    public void setPayAccountNumber(Long payAccountNumber) {
        this.payAccountNumber = payAccountNumber;
    }

    public String getPayAddress() {
        return payAddress;
    }

    public void setPayAddress(String payAddress) {
        this.payAddress = payAddress;
    }

    public Long getPayCardNumber() {
        return payCardNumber;
    }

    public void setPayCardNumber(Long payCardNumber) {
        this.payCardNumber = payCardNumber;
    }

    public int getPayCode() {
        return payCode;
    }

    public void setPayCode(int payCode) {
        this.payCode = payCode;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getPayDeliverPhone() {
        return payDeliverPhone;
    }

    public void setPayDeliverPhone(String payDeliverPhone) {
        this.payDeliverPhone = payDeliverPhone;
    }

    public String getPayDeliverStatus() {
        return payDeliverStatus;
    }

    public void setPayDeliverStatus(String payDeliverStatus) {
        this.payDeliverStatus = payDeliverStatus;
    }

    public String getPayReceiver() {
        return payReceiver;
    }

    public void setPayReceiver(String payReceiver) {
        this.payReceiver = payReceiver;
    }

    public String getPayRefundYn() {
        return payRefundYn;
    }

    public void setPayRefundYn(String payRefundYn) {
        this.payRefundYn = payRefundYn;
    }

    public int getPayTotalCnt() {
        return payTotalCnt;
    }

    public void setPayTotalCnt(int payTotalCnt) {
        this.payTotalCnt = payTotalCnt;
    }

    public int getPayTotalPrice() {
        return payTotalPrice;
    }

    public void setPayTotalPrice(int payTotalPrice) {
        this.payTotalPrice = payTotalPrice;
    }

    @Override
    public String toString() {
        return "PayDTO{" +
                "bankCode=" + bankCode +
                ", payCode=" + payCode +
                ", memberCode=" + memberCode +
                ", payDate='" + payDate + '\'' +
                ", payTotalCnt=" + payTotalCnt +
                ", payTotalPrice=" + payTotalPrice +
                ", payAddress='" + payAddress + '\'' +
                ", payReceiver='" + payReceiver + '\'' +
                ", payDeliverPhone='" + payDeliverPhone + '\'' +
                ", payDeliverStatus='" + payDeliverStatus + '\'' +
                ", payRefundYn='" + payRefundYn + '\'' +
                ", payAccountNumber=" + payAccountNumber +
                ", payCardNumber=" + payCardNumber +
                ", cardCompanyCode=" + cardCompanyCode +
                '}';
    }
}
