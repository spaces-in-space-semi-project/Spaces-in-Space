package com.space.spacesinspace.common.dto;

public class PayDetailDTO {

    private int payDetailCode;          // 결제상세코드
    private int payCode;                // 결제코드
    private int productCode;            // 상품코드
    private int payDetailCnt;           // 결제수량
    private int payDetailPrice;         // 결제 금액

    public PayDetailDTO() {
    }

    public PayDetailDTO(int payDetailCode, int payCode, int productCode, int payDetailCnt, int payDetailPrice) {
        this.payDetailCode = payDetailCode;
        this.payCode = payCode;
        this.productCode = productCode;
        this.payDetailCnt = payDetailCnt;
        this.payDetailPrice = payDetailPrice;
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

    @Override
    public String toString() {
        return "PayDetailDTO{" +
                "payDetailCode=" + payDetailCode +
                ", payCode=" + payCode +
                ", productCode=" + productCode +
                ", payDetailCnt=" + payDetailCnt +
                ", payDetailPrice=" + payDetailPrice +
                '}';
    }
}
