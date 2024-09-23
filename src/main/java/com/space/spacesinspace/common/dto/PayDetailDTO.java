package com.space.spacesinspace.common.dto;

public class PayDetailDTO {

    private int payDetailCode;      // 결제상세코드
    private int payCode;            // 결제코드
    private int productCode;        // 상품코드
    private int payDetailCnt;       // 결제수량
    private int payDetailPrice;     // 결제 금액
    private String payDate;         // 결제일자
    private String payRefundYn;     // 결제결제상태
    private String productName;     // 상품이름
    private String payReceiver;     // 받는분 성함
    private String payDeliverPhone; // 받는분 연락처
    private String payDeliverStatus;// 배송 상태
    private Long payAccountNumber;   // 계좌번호
    private Long payCardNumber;      // 카드번호
    private String bankName;           // 은행명
    private String cardCompanyName;    // 카드사명

    public PayDetailDTO() {
    }

    public PayDetailDTO(int payDetailCode, int payCode, int productCode, int payDetailCnt, int payDetailPrice, String payDate, String payRefundYn, String productName, String payReceiver, String payDeliverPhone, String payDeliverStatus, Long payAccountNumber, Long payCardNumber, String bankName, String cardCompanyName) {
        this.payDetailCode = payDetailCode;
        this.payCode = payCode;
        this.productCode = productCode;
        this.payDetailCnt = payDetailCnt;
        this.payDetailPrice = payDetailPrice;
        this.payDate = payDate;
        this.payRefundYn = payRefundYn;
        this.productName = productName;
        this.payReceiver = payReceiver;
        this.payDeliverPhone = payDeliverPhone;
        this.payDeliverStatus = payDeliverStatus;
        this.payAccountNumber = payAccountNumber;
        this.payCardNumber = payCardNumber;
        this.bankName = bankName;
        this.cardCompanyName = cardCompanyName;
    }

    public int getPayDetailCode() {
        return payDetailCode;
    }

    public void setPayDetailCode(int payDetailCode) {
        this.payDetailCode = payDetailCode;
    }

    public int getPayCode() {
        return payCode;
    }

    public void setPayCode(int payCode) {
        this.payCode = payCode;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public int getPayDetailCnt() {
        return payDetailCnt;
    }

    public void setPayDetailCnt(int payDetailCnt) {
        this.payDetailCnt = payDetailCnt;
    }

    public int getPayDetailPrice() {
        return payDetailPrice;
    }

    public void setPayDetailPrice(int payDetailPrice) {
        this.payDetailPrice = payDetailPrice;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardCompanyName() {
        return cardCompanyName;
    }

    public void setCardCompanyName(String cardCompanyName) {
        this.cardCompanyName = cardCompanyName;
    }

    public String getPayRefundYn() {
        return payRefundYn;
    }

    public void setPayRefundYn(String payRefundYn) {
        this.payRefundYn = payRefundYn;
    }

    @Override
    public String toString() {
        return "PayDetailDTO{" +
                "payDetailCode=" + payDetailCode +
                ", payCode=" + payCode +
                ", productCode=" + productCode +
                ", payDetailCnt=" + payDetailCnt +
                ", payDetailPrice=" + payDetailPrice +
                ", payDate='" + payDate + '\'' +
                ", payRefundYn='" + payRefundYn + '\'' +
                ", productName='" + productName + '\'' +
                ", payReceiver='" + payReceiver + '\'' +
                ", payDeliverPhone='" + payDeliverPhone + '\'' +
                ", payDeliverStatus='" + payDeliverStatus + '\'' +
                ", payAccountNumber=" + payAccountNumber +
                ", payCardNumber=" + payCardNumber +
                ", bankName=" + bankName +
                ", cardCompanyName=" + cardCompanyName +
                '}';
    }
}
