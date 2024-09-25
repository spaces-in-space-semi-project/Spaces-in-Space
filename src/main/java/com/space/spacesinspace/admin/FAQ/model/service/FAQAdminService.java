package com.space.spacesinspace.admin.FAQ.model.service;

import com.space.spacesinspace.admin.FAQ.model.dao.FAQAdminDAO;
import com.space.spacesinspace.common.dto.FAQDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FAQAdminService {

    private final FAQAdminDAO faqAdminDAO;

    @Autowired
    public FAQAdminService(FAQAdminDAO faqAdminDAO) {
        this.faqAdminDAO = faqAdminDAO;
    }

    // 모든 FAQ 목록 조회
    public List<FAQDTO> getAllFAQs() {
        return faqAdminDAO.getAllFAQs(); // DAO를 통해 모든 FAQ 조회
    }

    // 특정 faqCode로 FAQ 조회
    public FAQDTO getFAQByCode(String faqCode) {
        return faqAdminDAO.getFAQByCode(faqCode); // DAO를 통해 FAQ 조회
    }

    // 새로운 FAQ 생성
    public void createFAQ(FAQDTO faqDTO) {
        faqAdminDAO.insertFAQ(faqDTO); // DAO를 통해 새로운 FAQ 추가
    }

    // FAQ 수정
    public void updateFAQ(String faqCode, FAQDTO faqDTO) {
        faqDTO.setFaqCode(faqCode); // DTO에 faqCode 설정
        faqAdminDAO.updateFAQ(faqDTO); // DAO를 통해 FAQ 업데이트
    }

    // FAQ 삭제
    public void deleteFAQ(String faqCode) {
        faqAdminDAO.deleteFAQ(faqCode); // DAO를 통해 FAQ 삭제
    }
}
