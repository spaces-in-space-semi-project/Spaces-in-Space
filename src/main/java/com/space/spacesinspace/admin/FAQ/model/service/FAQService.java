package com.space.spacesinspace.user.FAQ.model.service;

import com.space.spacesinspace.user.FAQ.model.dao.FAQDAO;
import com.space.spacesinspace.user.FAQ.model.dto.FAQDTO;

import java.util.List;

public class FAQService {
    private FAQDAO faqDAO;

    public FAQService(FAQDAO faqDAO) {
        this.faqDAO = faqDAO;
    }

    public FAQDTO getFAQByCode(String code) {
        return faqDAO.getFAQByCode(code);
    }

    public List<FAQDTO> getAllFAQs() {
        return faqDAO.getAllFAQs();
    }

    public void addFAQ(FAQDTO faq) {
        faqDAO.insertFAQ(faq);
    }

    public void updateFAQ(FAQDTO faq) {
        faqDAO.updateFAQ(faq);
    }

    public void deleteFAQ(String code) {
        faqDAO.deleteFAQ(code);
    }
}

