package com.space.spacesinspace.user.pay.model.dto;

public class CardCompanyDTO {

    private int cardCompanyCode;    // 카드사코드
    private String cardCompanyName; // 카드사명

    public CardCompanyDTO() {
    }

    public CardCompanyDTO(int cardCompanyCode, String cardCompanyName) {
        this.cardCompanyCode = cardCompanyCode;
        this.cardCompanyName = cardCompanyName;
    }

    public int getCardCompanyCode() {
        return cardCompanyCode;
    }

    public void setCardCompanyCode(int cardCompanyCode) {
        this.cardCompanyCode = cardCompanyCode;
    }

    public String getCardCompanyName() {
        return cardCompanyName;
    }

    public void setCardCompanyName(String cardCompanyName) {
        this.cardCompanyName = cardCompanyName;
    }

    @Override
    public String toString() {
        return "CardCompanyDTO{" +
                "cardCompanyCode=" + cardCompanyCode +
                ", cardCompanyName='" + cardCompanyName + '\'' +
                '}';
    }
}
