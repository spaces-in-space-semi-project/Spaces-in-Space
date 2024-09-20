package com.space.spacesinspace.common.dto;

public class CartDTO {

    private int productCode;    // 상품코드
    private int memberCode;     // 회원코드
    private int cartCnt;        // 장바구니 개수
    private int cartPrice;      // 장바구니 가격

    public CartDTO() {
    }

    public CartDTO(int productCode, int memberCode, int cartCnt, int cartPrice) {
        this.productCode = productCode;
        this.memberCode = memberCode;
        this.cartCnt = cartCnt;
        this.cartPrice = cartPrice;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }

    public int getCartCnt() {
        return cartCnt;
    }

    public void setCartCnt(int cartCnt) {
        this.cartCnt = cartCnt;
    }

    public int getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(int cartPrice) {
        this.cartPrice = cartPrice;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "productCode=" + productCode +
                ", memberCode=" + memberCode +
                ", cartCnt=" + cartCnt +
                ", cartPrice=" + cartPrice +
                '}';
    }
}
