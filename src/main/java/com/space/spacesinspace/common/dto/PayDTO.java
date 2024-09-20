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
    private String payRefundYn;         // 결제취소여부
    private Long payAccountNumber;       // 계좌번호
    private Long payCardNumber;          // 카드번호
    private int bankCode;               // 은행코드
    private int cardCompanyCode;        // 카드사코드

    public PayDTO() {
    }

    public PayDTO(int payCode, int memberCode, String payDate, int payTotalCnt, int payTotalPrice, String payAddress, String payReceiver, String payDeliverPhone, String payDeliverStatus, String payRefundYn, Long payAccountNumber, Long payCardNumber, int bankCode, int cardCompanyCode) {
        this.payCode = payCode;
        this.memberCode = memberCode;
        this.payDate = payDate;
        this.payTotalCnt = payTotalCnt;
        this.payTotalPrice = payTotalPrice;
        this.payAddress = payAddress;
        this.payReceiver = payReceiver;
        this.payDeliverPhone = payDeliverPhone;
        this.payDeliverStatus = payDeliverStatus;
        this.payRefundYn = payRefundYn;
        this.payAccountNumber = payAccountNumber;
        this.payCardNumber = payCardNumber;
        this.bankCode = bankCode;
        this.cardCompanyCode = cardCompanyCode;
    }

    public int getPayCode() {
        return payCode;
    }

    public void setPayCode(int payCode) {
        this.payCode = payCode;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
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

    public String getPayAddress() {
        return payAddress;
    }

    public void setPayAddress(String payAddress) {
        this.payAddress = payAddress;
    }

    public String getPayReceiver() {
        return payReceiver;
    }

    public void setPayReceiver(String payReceiver) {
        this.payReceiver = payReceiver;
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

    public String getPayRefundYn() {
        return payRefundYn;
    }

    public void setPayRefundYn(String payRefundYn) {
        this.payRefundYn = payRefundYn;
    }

    public Long getPayAccountNumber() {
        return payAccountNumber;
    }

    public void setPayAccountNumber(Long payAccountNumber) {
        this.payAccountNumber = payAccountNumber;
    }

    public Long getPayCardNumber() {
        return payCardNumber;
    }

    public void setPayCardNumber(Long payCardNumber) {
        this.payCardNumber = payCardNumber;
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

    @Override
    public String toString() {
        return "PayDTO{" +
                "payCode=" + payCode +
                ", memberCode=" + memberCode +
                ", payDate=" + payDate +
                ", payTotalCnt=" + payTotalCnt +
                ", payTotalPrice=" + payTotalPrice +
                ", payAddress='" + payAddress + '\'' +
                ", payReceiver='" + payReceiver + '\'' +
                ", payDeliverPhone='" + payDeliverPhone + '\'' +
                ", payDeliverStatus='" + payDeliverStatus + '\'' +
                ", payRefundYn='" + payRefundYn + '\'' +
                ", payAccountNumber=" + payAccountNumber +
                ", payCardNumber=" + payCardNumber +
                ", bankCode=" + bankCode +
                ", cardCompanyCode=" + cardCompanyCode +
                '}';
    }
}
