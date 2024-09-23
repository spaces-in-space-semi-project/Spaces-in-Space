package com.space.spacesinspace.user.FAQ.model.service;

import com.space.spacesinspace.user.FAQ.model.dao.FAQDAO;
import com.space.spacesinspace.common.dto.FAQDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FAQService {


    private FAQDAO faqDAO;

    @Autowired
    public FAQService(FAQDAO faqDAO) {
        this.faqDAO = faqDAO;
    }

    public FAQDTO getFAQByCode(String code) {
        return faqDAO.getFAQByCode(code);
    }

    public List<FAQDTO> getAllFAQs() {
        return faqDAO.getAllFAQs();
    }
}
