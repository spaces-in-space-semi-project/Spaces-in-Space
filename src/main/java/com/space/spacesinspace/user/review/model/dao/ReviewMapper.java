package com.space.spacesinspace.user.review.model.dao;

import com.space.spacesinspace.user.review.model.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {

    List<ReviewDTO> findAllReviewBy(int memberCode);

    ReviewDTO findReviewByCode(int reviewCode);

    int registNewReview(ReviewDTO newReview);

    int deleteReview(int code);

    int updateReview(ReviewDTO review);

    int updatePayDetailReviewYnInsert(int payDetailCode);

    int updatePayDetailReviewYnDelete(int payDetailCode);

    List<ReviewDTO> findReviewByProductCode(int productCode);
}
