package com.space.spacesinspace.user.review.controller;

import com.space.spacesinspace.user.review.model.dto.ReviewDTO;
import com.space.spacesinspace.common.dto.MemberDTO;
import com.space.spacesinspace.user.review.model.service.ReviewService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user/review/*")
public class ReviewController {

    private final ResourceLoader resourceLoader;
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ResourceLoader resourceLoader, ReviewService reviewService) {
        this.resourceLoader = resourceLoader;
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

    @GetMapping("regist/{productCode}")
    public String registPage(@PathVariable int productCode, Model model) {

        model.addAttribute("productCode", productCode);
        model.addAttribute("activeSection", "reviewRegist");
        return "user/member/myPage";
    }

    @PostMapping("regist")
    public ModelAndView registReview(@RequestParam("reviewTitle") String reviewTitle,
                               @RequestParam("reviewDetail") String reviewDetail,
                               @RequestParam("reviewRating") int reviewRating,
                               @RequestParam("productCode") int productCode,
                               @AuthenticationPrincipal MemberDTO member,
                               @RequestParam("reviewPhotoOriginal") MultipartFile reviewPhotoOriginal,
                               ModelAndView mv, RedirectAttributes rAttr) throws IOException {
        ReviewDTO newReview = new ReviewDTO();
        newReview.setReviewTitle(reviewTitle);
        newReview.setReviewDetail(reviewDetail);
        newReview.setReviewRating(reviewRating);
        newReview.setProductCode(productCode);

        int memberCode = member.getMemberCode();
        newReview.setMemberCode(memberCode);

        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        newReview.setReviewDate(formattedDate);

        // directory 선언
        Resource resource = resourceLoader.getResource("classpath:static/uploadedFiles/img");
        String filePath = null;
        if (!resource.exists()) {
            // 경로가 존재하지 않을 때
            String root = "src/main/resources/static/uploadedFiles/img";

            File file = new File(root);
            file.mkdirs();

            filePath = file.getAbsolutePath();
        } else {
            // 경로가 이미 존재할 때
            filePath = resourceLoader.getResource("classpath:static/uploadedFiles/img")
                    .getFile()
                    .getAbsolutePath();
        }

        // 랜덤으로 유니크한 파일명 생성
        String originalFileName = reviewPhotoOriginal.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedName = UUID.randomUUID().toString().replace("-", "") + extension;
        File originalFile = new File(filePath + "/" + savedName);
        String thumbnailName = savedName.replace(extension, "_thumbnail" + extension);
        File thumbnailFile = new File(filePath + "/" + thumbnailName);

        try {
            // 파일 저장
            reviewPhotoOriginal.transferTo(originalFile);

            // Thumbnailator 사용하여 썸네일 생성
            Thumbnails.of(originalFile)
                    .size(160, 90) // Define thumbnail dimensions
                    .toFile(thumbnailFile);

            // DTO에 저장된 파일의 경로를 String으로 저장
            newReview.setReviewPhotoOriginal("/uploadedFiles/img/" + savedName);
            newReview.setReviewPhotoThumbnail("/uploadedFiles/img/" + thumbnailName);

            // 상품 등록 로직
            Integer result = reviewService.registNewReview(newReview);

            String message;
            if (result == null || result == 0) {
                message = "상품등록에 실패했습니다. 다시 시도해주세요.";
                mv.setViewName("redirect:/admin/product/productRegist");
            } else {
                message = "상품을 성공적으로 등록했습니다.";
                mv.setViewName("redirect:/admin/product/productsManage");
            }

            rAttr.addFlashAttribute("message", message);

        } catch (IOException e) {
            // Handle exceptions and rollback if needed
            if (originalFile.exists()) {
                originalFile.delete();
            }
            if (thumbnailFile.exists()) {
                thumbnailFile.delete();
            }
            System.err.println("[Failed] 파일 업로드 또는 썸네일 생성 실패");
            e.printStackTrace();

            rAttr.addFlashAttribute("message", "파일 업로드 중 오류가 발생했습니다. 다시 시도해주세요.");
            mv.setViewName("redirect:/user/review/regist");
        }

        return mv;
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
