package com.space.spacesinspace.user.review.model.service;

import com.space.spacesinspace.user.review.model.dao.ReviewMapper;
import com.space.spacesinspace.user.review.model.dto.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewService(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    public List<ReviewDTO> findAllReviewBy(int memberCode) {
        return reviewMapper.findAllReviewBy(memberCode);
    }

    public ReviewDTO findReviewByCode(int code) {
        return reviewMapper.findReviewByCode(code);
    }

    public void registNewReview(ReviewDTO newReview) {
        reviewMapper.registNewReview(newReview);
    }

    public void deleteReview(int code) {
        reviewMapper.deleteReview(code);
    }

    public void updateReview(ReviewDTO review) {
        reviewMapper.updateReview(review);
    }
}
