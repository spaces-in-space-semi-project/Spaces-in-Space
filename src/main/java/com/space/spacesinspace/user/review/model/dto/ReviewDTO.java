package com.space.spacesinspace.user.review.model.dto;

public class ReviewDTO {

    private int reviewCode;
    private int productCode;
    private int memberCode;
    private String reviewTitle;
    private String reviewDetail;
    private String reviewPhotoOriginal;
    private String reviewPhotoThumbnail;
    private int reviewRating;
    private String reviewDate;
    private String reviewEditDate;

    public ReviewDTO() {
    }

    public ReviewDTO(int reviewCode, int productCode, int memberCode, String reviewTitle, String reviewDetail, String reviewPhotoOriginal, String reviewPhotoThumbnail, int reviewRating, String reviewDate, String reviewEditDate) {
        this.reviewCode = reviewCode;
        this.productCode = productCode;
        this.memberCode = memberCode;
        this.reviewTitle = reviewTitle;
        this.reviewDetail = reviewDetail;
        this.reviewPhotoOriginal = reviewPhotoOriginal;
        this.reviewPhotoThumbnail = reviewPhotoThumbnail;
        this.reviewRating = reviewRating;
        this.reviewDate = reviewDate;
        this.reviewEditDate = reviewEditDate;
    }

    public int getReviewCode() {
        return reviewCode;
    }

    public void setReviewCode(int reviewCode) {
        this.reviewCode = reviewCode;
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

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewDetail() {
        return reviewDetail;
    }

    public void setReviewDetail(String reviewDetail) {
        this.reviewDetail = reviewDetail;
    }

    public String getReviewPhotoOriginal() {
        return reviewPhotoOriginal;
    }

    public void setReviewPhotoOriginal(String reviewPhotoOriginal) {
        this.reviewPhotoOriginal = reviewPhotoOriginal;
    }

    public String getReviewPhotoThumbnail() {
        return reviewPhotoThumbnail;
    }

    public void setReviewPhotoThumbnail(String reviewPhotoThumbnail) {
        this.reviewPhotoThumbnail = reviewPhotoThumbnail;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewEditDate() {
        return reviewEditDate;
    }

    public void setReviewEditDate(String reviewEditDate) {
        this.reviewEditDate = reviewEditDate;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "reviewCode=" + reviewCode +
                ", productCode=" + productCode +
                ", memberCode=" + memberCode +
                ", reviewTitle='" + reviewTitle + '\'' +
                ", reviewDetail='" + reviewDetail + '\'' +
                ", reviewPhotoOriginal='" + reviewPhotoOriginal + '\'' +
                ", reviewPhotoThumbnail='" + reviewPhotoThumbnail + '\'' +
                ", reviewRating=" + reviewRating +
                ", reviewDate='" + reviewDate + '\'' +
                ", reviewEditDate='" + reviewEditDate + '\'' +
                '}';
    }
}
