package com.space.spacesinspace.admin.FAQ.model.service;

import com.space.spacesinspace.admin.FAQ.model.dao.FAQAdminDAO;
import com.space.spacesinspace.common.dto.FAQDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FAQAdminService {

    @Autowired
    private FAQAdminDAO faqDAO;

    public FAQAdminService(FAQAdminDAO faqDAO) {
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

