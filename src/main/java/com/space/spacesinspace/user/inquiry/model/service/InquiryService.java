package com.space.spacesinspace.user.inquiry.model.service;

import com.space.spacesinspace.common.dto.InquiryDTO;
import com.space.spacesinspace.common.dto.ReplyDTO;
import com.space.spacesinspace.user.inquiry.model.dao.InquiryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InquiryService {

    private final InquiryMapper inquiryMapper;

    @Autowired
    public InquiryService(InquiryMapper inquiryMapper) {
        this.inquiryMapper = inquiryMapper;
    }

    public List<InquiryDTO> findAllInquiryBy(int memberCode) {
        return inquiryMapper.findAllInquiryBy(memberCode);
    }

    public InquiryDTO findInquiryByCode(int inquiryCode) {
        return inquiryMapper.findInquiryByCode(inquiryCode);
    }

    @Transactional
    public void deleteInquiry(int code) {
        inquiryMapper.deleteInquiry(code);
    }

    @Transactional
    public void registNewInquiry(InquiryDTO newInquiry) {
        inquiryMapper.registNewInquiry(newInquiry);
    }

    @Transactional
    public Integer updateInquiry(InquiryDTO inquiry) {
        return inquiryMapper.updateInquiry(inquiry);
    }

    public ReplyDTO findReplyByCode(int inquiryCode) {
        return inquiryMapper.findReplyByCode(inquiryCode);
    }
}
