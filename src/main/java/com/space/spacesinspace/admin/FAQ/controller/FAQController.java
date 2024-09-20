package com.space.spacesinspace.user.FAQ.controller;

import com.space.spacesinspace.user.FAQ.model.dto.FAQDTO;
import com.space.spacesinspace.user.FAQ.model.service.FAQService;

import java.util.List;

public class FAQController {
    private FAQService faqService;

    public FAQController(FAQService faqService) {
        this.faqService = faqService;
    }

    public FAQDTO getFAQ(String code) {
        return faqService.getFAQByCode(code);
    }

    public List<FAQDTO> listFAQs() {
        return faqService.getAllFAQs();
    }

    public void createFAQ(FAQDTO faq) {
        faqService.addFAQ(faq);
    }

    public void modifyFAQ(FAQDTO faq) {
        faqService.updateFAQ(faq);
    }

    public void removeFAQ(String code) {
        faqService.deleteFAQ(code);
    }
}

