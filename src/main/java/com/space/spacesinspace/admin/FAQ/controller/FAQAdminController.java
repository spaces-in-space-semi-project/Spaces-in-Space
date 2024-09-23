package com.space.spacesinspace.admin.FAQ.controller;

import com.space.spacesinspace.common.dto.FAQDTO;
import com.space.spacesinspace.admin.FAQ.model.service.FAQAdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin/faq/*")
public class FAQAdminController {

    private final FAQAdminService faqAdminService;

    public FAQAdminController(FAQAdminService faqAdminService) {
        this.faqAdminService = faqAdminService;
    }

    @GetMapping("/list")
    public String faq(Model model) {
        List<FAQDTO> faqs = faqAdminService.getAllFAQs();
        model.addAttribute("faqs", faqs);
        return "faq/faq";
    }
}

