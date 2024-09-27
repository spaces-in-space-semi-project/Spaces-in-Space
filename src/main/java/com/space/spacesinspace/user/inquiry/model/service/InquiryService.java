package com.space.spacesinspace.user.inquiry.model.service;

import com.space.spacesinspace.common.dto.InquiryDTO;
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

    public void deleteInquiry(int code) {
        inquiryMapper.deleteInquiry(code);
    }

    public void registNewInquiry(InquiryDTO newInquiry) {
        inquiryMapper.registNewInquiry(newInquiry);
    }

    public void updateInquiry(InquiryDTO inquiry) throws Exception{
        inquiryMapper.updateInquiry(inquiry);
    }
}
