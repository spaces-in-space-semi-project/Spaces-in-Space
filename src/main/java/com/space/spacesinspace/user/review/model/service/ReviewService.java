package com.space.spacesinspace.user.review.model.service;

import com.space.spacesinspace.user.review.model.dao.ReviewMapper;
import com.space.spacesinspace.user.review.model.dto.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public ReviewDTO findReviewByCode(int reviewCode) {
        return reviewMapper.findReviewByCode(reviewCode);
    }

    @Transactional
    public Integer registNewReview(ReviewDTO newReview) {
        return reviewMapper.registNewReview(newReview);
    }

    @Transactional
    public Integer deleteReview(int code) {
        return reviewMapper.deleteReview(code);
    }

    @Transactional
    public Integer updateReview(ReviewDTO review) {
        return reviewMapper.updateReview(review);
    }

    @Transactional
    public Integer updatePayDetailReviewYnInsert(int payDetailCode) {
        return reviewMapper.updatePayDetailReviewYnInsert(payDetailCode);
    }

    @Transactional
    public Integer updatePayDetailReviewYnDelete(int payDetailCode) {
        return reviewMapper.updatePayDetailReviewYnDelete(payDetailCode);
    }
}
