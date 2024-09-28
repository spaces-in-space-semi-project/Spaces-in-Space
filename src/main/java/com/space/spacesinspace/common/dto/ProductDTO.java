package com.space.spacesinspace.common.dto;

public class ProductDTO {
    private int productCode;
    private int categoryCode;
    private String categoryName;
    private String productName;
    private String productImageOriginal;
    private String productImageThumbnail;
    private int productPrice;
    private String productDeliverTime;
    private int productDelieverCost;
    private String productSize;
    private String productMaterial;
    private String productDescription;
    private String productDeleteYn;

    public ProductDTO() {
    }

    public ProductDTO(int productCode, int categoryCode, String categoryName, String productName, String productImageOriginal, String productImageThumbnail, int productPrice, String productDeliverTime, int productDelieverCost, String productSize, String productMaterial, String productDescription, String productDeleteYn) {
        this.productCode = productCode;
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.productName = productName;
        this.productImageOriginal = productImageOriginal;
        this.productImageThumbnail = productImageThumbnail;
        this.productPrice = productPrice;
        this.productDeliverTime = productDeliverTime;
        this.productDelieverCost = productDelieverCost;
        this.productSize = productSize;
        this.productMaterial = productMaterial;
        this.productDescription = productDescription;
        this.productDeleteYn = productDeleteYn;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImageOriginal() {
        return productImageOriginal;
    }

    public void setProductImageOriginal(String productImageOriginal) {
        this.productImageOriginal = productImageOriginal;
    }

    public String getProductImageThumbnail() {
        return productImageThumbnail;
    }

    public void setProductImageThumbnail(String productImageThumbnail) {
        this.productImageThumbnail = productImageThumbnail;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDeliverTime() {
        return productDeliverTime;
    }

    public void setProductDeliverTime(String productDeliverTime) {
        this.productDeliverTime = productDeliverTime;
    }

    public int getProductDelieverCost() {
        return productDelieverCost;
    }

    public void setProductDelieverCost(int productDelieverCost) {
        this.productDelieverCost = productDelieverCost;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getProductMaterial() {
        return productMaterial;
    }

    public void setProductMaterial(String productMaterial) {
        this.productMaterial = productMaterial;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductDeleteYn() {
        return productDeleteYn;
    }

    public void setProductDeleteYn(String productDeleteYn) {
        this.productDeleteYn = productDeleteYn;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productCode=" + productCode +
                ", categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", productName='" + productName + '\'' +
                ", productImageOriginal='" + productImageOriginal + '\'' +
                ", productImageThumbnail='" + productImageThumbnail + '\'' +
                ", productPrice=" + productPrice +
                ", productDeliverTime='" + productDeliverTime + '\'' +
                ", productDelieverCost=" + productDelieverCost +
                ", productSize='" + productSize + '\'' +
                ", productMaterial='" + productMaterial + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productDeleteYn='" + productDeleteYn + '\'' +
                '}';
    }
}


