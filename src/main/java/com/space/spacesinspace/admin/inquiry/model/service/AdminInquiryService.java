package com.space.spacesinspace.admin.inquiry.model.service;

import com.space.spacesinspace.admin.inquiry.model.dao.AdminInquiryMapper;
import com.space.spacesinspace.admin.reply.model.dao.ReplyMapper;
import com.space.spacesinspace.common.dto.InquiryDTO;
import com.space.spacesinspace.common.dto.ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminInquiryService {

    private final AdminInquiryMapper adminInquiryMapper;

    @Autowired
    public AdminInquiryService(AdminInquiryMapper adminInquiryMapper) {
        this.adminInquiryMapper = adminInquiryMapper;
    }

    public List<InquiryDTO> findAllInquiry() {
        return adminInquiryMapper.findAllInquiry();
    }

    public InquiryDTO findInquiryByCode(int inquiryCode) {
        return adminInquiryMapper.findInquiryByCode(inquiryCode);
    }

    @Transactional
    public void deleteInquiry(int inquiryCode) {
        adminInquiryMapper.deleteInquiry(inquiryCode);
    }

    public ReplyDTO findReplyByCode(int inquiryCode) {
        return adminInquiryMapper.findReplyByCode(inquiryCode);
    }

    public List<InquiryDTO> findInquiryBySearch(String searchValue) {
        return adminInquiryMapper.findInquiryBySearch(searchValue);
    }
}
