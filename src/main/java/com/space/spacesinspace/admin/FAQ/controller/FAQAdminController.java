    package com.space.spacesinspace.admin.FAQ.controller;

    import com.space.spacesinspace.admin.FAQ.model.service.FAQAdminService;
    import com.space.spacesinspace.common.dto.FAQDTO;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.servlet.ModelAndView;

    import java.util.List;

    @Controller
    @RequestMapping("admin/faq/*")
    public class FAQAdminController {

        private final FAQAdminService faqAdminService;

        public FAQAdminController(FAQAdminService faqAdminService) {
            this.faqAdminService = faqAdminService;
        }

        // 1. Read: FAQ 목록 조회
        @GetMapping("/list")
        public ModelAndView listFAQs(ModelAndView mv) {
            List<FAQDTO> faqList = faqAdminService.getAllFAQs(); // 모든 FAQ 목록을 가져옴
            mv.addObject("faqList", faqList); // FAQ 목록을 뷰에 전달
            mv.addObject("activeSection", "faqList");
            mv.setViewName("admin/layout/adminLayout");
            return mv;
        }

        // 2. Create: FAQ 생성 폼 보여주기
        @GetMapping("/create")
        public String createFaqForm(Model model) {
            model.addAttribute("faqDTO", new FAQDTO()); // 빈 FAQDTO 객체를 뷰에 전달
            model.addAttribute("activeSection", "createFaq");
            return "admin/layout/adminLayout";
        }

        // 2. Create: 새로운 FAQ 생성
        @PostMapping("/create")
        public String createFaq(@ModelAttribute FAQDTO faqDTO) {
            faqAdminService.createFAQ(faqDTO); // 새로운 FAQ 생성
            return "redirect:/admin/faq/list"; // FAQ 목록 페이지로 리다이렉트
        }

        // 3. Update: 수정할 FAQ를 조회하여 수정 폼 보여주기
        @GetMapping("/update/{faqCode}")
        public String updateFaqForm(@PathVariable("faqCode") String faqCode, Model model) {
            FAQDTO faqDTO = faqAdminService.getFAQByCode(faqCode); // 수정할 FAQ를 조회
            model.addAttribute("faqDTO", faqDTO); // 조회된 FAQ 데이터를 뷰에 전달
            model.addAttribute("activeSection", "updateFaq");
            return "admin/layout/adminLayout";
        }

        // 3. Update: 수정된 FAQ 데이터를 저장
        @PostMapping("/update/{faqCode}")
        public String updateFaq(@PathVariable("faqCode") String faqCode, @ModelAttribute FAQDTO faqDTO) {
            faqAdminService.updateFAQ(faqCode, faqDTO); // 수정된 FAQ 데이터를 저장
            return "redirect:/admin/faq/list"; // FAQ 목록 페이지로 리다이렉트
        }

        // 4. Delete: FAQ 삭제
        @PostMapping("/delete/{faqCode}")
        public String deleteFaq(@PathVariable("faqCode") String faqCode) {
            faqAdminService.deleteFAQ(faqCode); // FAQ 삭제
            return "redirect:/admin/faq/list"; // FAQ 목록 페이지로 리다이렉트
        }
    }
