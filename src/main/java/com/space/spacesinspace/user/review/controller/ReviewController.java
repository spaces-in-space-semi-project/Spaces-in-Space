package com.space.spacesinspace.user.review.controller;

import com.space.spacesinspace.user.review.model.dto.ReviewDTO;
import com.space.spacesinspace.common.dto.MemberDTO;
import com.space.spacesinspace.user.review.model.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user/review/*")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/list")
    public String findAllReview(Model model, @AuthenticationPrincipal MemberDTO member) {

        int memberCode = member.getMemberCode();

        List<ReviewDTO> reviewList = reviewService.findAllReviewBy(memberCode);

        model.addAttribute("reviewList", reviewList);
        model.addAttribute("memberName", "회원");
        model.addAttribute("activeSection", "review");

        return "user/member/myPage";
    }

    @GetMapping("/detail/{code}")
    public String findReviewByCode(@PathVariable("code") int code,
                                   Model model) {

        ReviewDTO review = reviewService.findReviewByCode(code);

        model.addAttribute("review", review);

        return "user/review/detail";
    }

    @GetMapping("regist")
    public void registPage() {}

    @PostMapping("regist")
    public String registReview(ReviewDTO newReview, RedirectAttributes rAttr) {
        reviewService.registNewReview(newReview);

        rAttr.addFlashAttribute("successMessage", "새로운 리뷰가 등록되었습니다.");

        return "redirect:/user/review/list";
    }

    @PostMapping("/delete/{code}")
    public String deleteReview(@PathVariable("code") int code, RedirectAttributes rAttr) {

        reviewService.deleteReview(code);

        rAttr.addFlashAttribute("successMessage", "리뷰가 삭제되었습니다.");

        return "redirect:/user/review/list";
    }

    @PostMapping("/update")
    public String updateReview(ReviewDTO review, RedirectAttributes rAttr) {

        reviewService.updateReview(review);

        rAttr.addFlashAttribute("successMessage", "리뷰가 수정되었습니다.");

        return "redirect:/user/review/detail" + review.getReviewCode();
    }
}
