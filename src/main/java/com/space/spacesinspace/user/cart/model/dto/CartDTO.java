package com.space.spacesinspace.user.cart.model.dto;

public class CartDTO {

    private int productCode;    // 상품코드
    private int productPrice;   // 상품가격
    private int memberCode;     // 회원코드
    private String productName; // 상품이름
    private String memberName;  // 회원이름
    private int cartCnt;        // 장바구니 개수
    private int cartPrice;      // 장바구니 가격
    private int totalCartCnt;   // 장바구니 총 수량
    private int totalCartPrice;  // 장바구니 총 금액

    public CartDTO() {
    }

    public CartDTO(int productCode, int productPrice, int memberCode, String productName, String memberName, int cartCnt, int cartPrice, int totalCartCnt, int totalCartPrice) {
        this.productCode = productCode;
        this.productPrice = productPrice;
        this.memberCode = memberCode;
        this.productName = productName;
        this.memberName = memberName;
        this.cartCnt = cartCnt;
        this.cartPrice = cartPrice;
        this.totalCartCnt = totalCartCnt;
        this.totalCartPrice = totalCartPrice;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
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

    public int getTotalCartCnt() {
        return totalCartCnt;
    }

    public void setTotalCartCnt(int totalCartCnt) {
        this.totalCartCnt = totalCartCnt;
    }

    public int getTotalCartPrice() {
        return totalCartPrice;
    }

    public void setTotalCartPrice(int totalCartPrice) {
        this.totalCartPrice = totalCartPrice;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "productCode=" + productCode +
                ", productPrice=" + productPrice +
                ", memberCode=" + memberCode +
                ", productName='" + productName + '\'' +
                ", memberName='" + memberName + '\'' +
                ", cartCnt=" + cartCnt +
                ", cartPrice=" + cartPrice +
                ", totalCartCnt=" + totalCartCnt +
                ", totalCartPrice=" + totalCartPrice +
                '}';
    }
}
