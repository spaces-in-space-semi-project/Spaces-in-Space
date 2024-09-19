package com.space.spacesinspace.common.dto;

public class BankDTO {

    private int bankCode;       // 은행코드
    private String bankName;    // 은행명

    public BankDTO() {
    }

    public BankDTO(int bankCode, String bankName) {
        this.bankCode = bankCode;
        this.bankName = bankName;
    }

    public int getBankCode() {
        return bankCode;
    }

    public void setBankCode(int bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return "BankDTO{" +
                "bankCode=" + bankCode +
                ", bankName='" + bankName + '\'' +
                '}';
    }
}
