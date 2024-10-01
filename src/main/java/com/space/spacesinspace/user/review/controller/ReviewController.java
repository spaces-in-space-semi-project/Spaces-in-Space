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
import java.time.LocalDateTime;
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

    @GetMapping("/detail/{reviewCode}")
    public String findReviewByCode(@PathVariable("reviewCode") int reviewCode,
                                   Model model) {

        ReviewDTO review = reviewService.findReviewByCode(reviewCode);

        model.addAttribute("review", review);
        model.addAttribute("activeSection", "reviewDetails");

        return "user/member/myPage";
    }

    @GetMapping("regist/{productCode}/{payDetailCode}")
    public String registPage(@PathVariable int productCode, @PathVariable int payDetailCode, Model model) {
        model.addAttribute("productCode", productCode);
        model.addAttribute("payDetailCode", payDetailCode);
        model.addAttribute("activeSection", "reviewRegist");

        return "user/member/myPage";
    }

    @PostMapping("regist")
    public ModelAndView registReview(@RequestParam("reviewTitle") String reviewTitle,
                               @RequestParam("reviewDetail") String reviewDetail,
                               @RequestParam("reviewRating") int reviewRating,
                               @RequestParam("productCode") int productCode,
                               @RequestParam("payDetailCode") int payDetailCode,
                               @AuthenticationPrincipal MemberDTO member,
                               @RequestParam("reviewPhotoOriginal") MultipartFile reviewPhotoOriginal,
                               ModelAndView mv, RedirectAttributes rAttr) throws IOException {
        ReviewDTO newReview = new ReviewDTO();
        newReview.setReviewTitle(reviewTitle);
        newReview.setReviewDetail(reviewDetail);
        newReview.setPayDetailCode(payDetailCode);
        newReview.setReviewRating(reviewRating);
        newReview.setProductCode(productCode);

        System.out.println("Pay Detail Code: " + payDetailCode);
        System.out.println("productCode: " + productCode);

        int memberCode = member.getMemberCode();
        newReview.setMemberCode(memberCode);

        LocalDateTime currentDateTime = LocalDateTime.now();
        String formattedDateTime = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        newReview.setReviewDate(formattedDateTime);

        // directory 선언
        Resource resource = resourceLoader.getResource("classpath:static/uploadedFiles/img/review");
        String filePath = null;
        if (!resource.exists()) {
            // 경로가 존재하지 않을 때
            String root = "src/main/resources/static/uploadedFiles/img/review";

            File file = new File(root);
            file.mkdirs();

            filePath = file.getAbsolutePath();
        } else {
            // 경로가 이미 존재할 때
            filePath = resourceLoader.getResource("classpath:static/uploadedFiles/img/review")
                    .getFile()
                    .getAbsolutePath();
        }

        if (!reviewPhotoOriginal.isEmpty()) {
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
                        .size(160, 90) // thumbnail 사이즈
                        .toFile(thumbnailFile);

                // DTO에 저장된 파일의 경로를 String으로 저장
                newReview.setReviewPhotoOriginal("/uploadedFiles/img/review/" + savedName);
                newReview.setReviewPhotoThumbnail("/uploadedFiles/img/review/" + thumbnailName);

                // 리뷰 등록 로직
                Integer result = reviewService.registNewReview(newReview);
                result = reviewService.updatePayDetailReviewYnInsert(payDetailCode);

                String message;
                if (result == null || result == 0) {
                    message = "리뷰등록에 실패했습니다. 다시 시도해주세요.";
                    mv.setViewName("redirect:/user/review/regist");
                } else {
                    message = "리뷰를 성공적으로 등록했습니다.";
                    mv.setViewName("redirect:/user/review/list");
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
        } else {
            // 리뷰 등록 로직
            Integer result = reviewService.registNewReview(newReview);
            result = reviewService.updatePayDetailReviewYnInsert(payDetailCode);

            String message;
            if (result == null || result == 0) {
                message = "리뷰등록에 실패했습니다. 다시 시도해주세요.";
                mv.setViewName("redirect:/user/review/regist");
            } else {
                message = "리뷰를 성공적으로 등록했습니다.";
                mv.setViewName("redirect:/user/review/list");
            }
        }
        return mv;
    }

    /**
     * 리뷰 삭제
     */
    @PostMapping("/delete/{reviewCode}/{payDetailCode}")
    public ModelAndView deleteReview(@PathVariable("reviewCode") int reviewCode, @PathVariable("payDetailCode") int payDetailCode, RedirectAttributes rAttr, ModelAndView mv) {

        Integer result = reviewService.deleteReview(reviewCode);
        System.out.println(payDetailCode);
        result = reviewService.updatePayDetailReviewYnDelete(payDetailCode);

        String message;
        if (result == null || result == 0) {
            message = "리뷰삭제에 실패했습니다. 다시 시도해주세요.";
        } else {
            message = "리뷰를 성공적으로 삭제했습니다.";
        }

        rAttr.addFlashAttribute("message", message);
        mv.setViewName("redirect:/user/review/list");

        return mv;
    }

    /**
     * 리뷰 수정
     */
    @PostMapping("/update")
    public ModelAndView updateReview(@RequestParam("reviewTitle") String reviewTitle,
                               @RequestParam("reviewDetail") String reviewDetail,
                               @RequestParam("reviewRating") int reviewRating,
                               @RequestParam("productCode") int productCode,
                               @RequestParam("reviewCode") int reviewCode,
                               @RequestParam("existingPhotoOriginal") String existingPhotoOriginal,
                               @RequestParam("existingPhotoThumbnail") String existingPhotoThumbnail,
                               @AuthenticationPrincipal MemberDTO member,
                               @RequestParam("reviewPhotoOriginal") MultipartFile reviewPhotoOriginal,
                               ModelAndView mv, RedirectAttributes rAttr) throws IOException {

        ReviewDTO review = new ReviewDTO();
        review.setReviewCode(reviewCode);
        review.setReviewTitle(reviewTitle);
        review.setReviewDetail(reviewDetail);
        review.setReviewRating(reviewRating);
        review.setProductCode(productCode);

        int memberCode = member.getMemberCode();
        review.setMemberCode(memberCode);

        LocalDateTime currentDateTime = LocalDateTime.now();
        String formattedDateTime = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        review.setReviewEditDate(formattedDateTime);

        // directory 선언
        Resource resource = resourceLoader.getResource("classpath:static/uploadedFiles/img/review");
        String filePath = null;
        if (!resource.exists()) {
            // 경로가 존재하지 않을 때
            String root = "src/main/resources/static/uploadedFiles/img/review";

            File file = new File(root);
            file.mkdirs();

            filePath = file.getAbsolutePath();
        } else {
            // 경로가 이미 존재할 때
            filePath = resourceLoader.getResource("classpath:static/uploadedFiles/img/review")
                    .getFile()
                    .getAbsolutePath();
        }

        // 리뷰 사진을 새로 등록했을 경우에만 동작
        if (!reviewPhotoOriginal.isEmpty()) {
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
                        .size(160, 90) // thumbnail 사이즈
                        .toFile(thumbnailFile);

                // DTO에 저장된 파일의 경로를 String으로 저장
                review.setReviewPhotoOriginal("/uploadedFiles/img/review/" + savedName);
                review.setReviewPhotoThumbnail("/uploadedFiles/img/review/" + thumbnailName);

                // 리뷰 등록 로직
                Integer result = reviewService.registNewReview(review);

                String message;
                if (result == null || result == 0) {
                    message = "리뷰등록에 실패했습니다. 다시 시도해주세요.";
                    mv.setViewName("redirect:/user/review/list");
                } else {
                    message = "리뷰를 성공적으로 등록했습니다.";
                    mv.setViewName("redirect:/user/review/list");
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
                mv.setViewName("redirect:/user/review/list");
            }
        } else {
            if (!(existingPhotoOriginal == null || existingPhotoThumbnail == null || existingPhotoOriginal.isEmpty() || existingPhotoThumbnail.isEmpty())) {
                review.setReviewPhotoOriginal(existingPhotoOriginal);
                review.setReviewPhotoThumbnail(existingPhotoThumbnail);
            }
        }

        // 업데이트된 정보 저장
        Integer result = reviewService.updateReview(review);

        String message;
        if (result == null || result == 0) {
            message = "리뷰수정에 실패했습니다. 다시 시도해주세요.";
        } else {
            message = "리뷰를 성공적으로 수정했습니다.";
        }

        rAttr.addFlashAttribute("message", message);
        mv.setViewName("redirect:/user/review/list");

        return mv;
    }
}
