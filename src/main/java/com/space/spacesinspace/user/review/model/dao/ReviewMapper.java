package com.space.spacesinspace.user.review.model.dao;

import com.space.spacesinspace.user.review.model.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {

    List<ReviewDTO> findAllReviewBy(int memberCode);

    ReviewDTO findReviewByCode(int code);

    int registNewReview(ReviewDTO newReview);

    void deleteReview(int code);

    void updateReview(ReviewDTO review);
}
