package com.space.spacesinspace.user.FAQ.controller;

import com.space.spacesinspace.common.dto.FAQDTO;
import com.space.spacesinspace.user.FAQ.model.service.FAQService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("user/faq/*")
public class FAQController {

    private final FAQService faqService;

    public FAQController(FAQService faqService) {
        this.faqService = faqService;
    }

    @GetMapping("/list")
    public String userFaq(Model model) {

        List<FAQDTO> faqList = faqService.getAllFAQs();
        model.addAttribute("faqList", faqList);
        return "user/faq/userFaq";
    }
}
