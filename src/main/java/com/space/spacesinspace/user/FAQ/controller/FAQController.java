package com.space.spacesinspace.user.FAQ.controller;

import com.space.spacesinspace.user.FAQ.model.service.FAQService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/faq/*")
public class FAQController {

    private final FAQService faqService;

    public FAQController(FAQService faqService) {
        this.faqService = faqService;
    }

    @GetMapping("/list")
    public String userFaq() {
        return "user/faq/userFaq";
    }
}
