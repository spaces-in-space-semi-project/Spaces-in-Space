package com.space.spacesinspace.user.inquiry.model.service;

import com.space.spacesinspace.common.dto.InquiryDTO;
import com.space.spacesinspace.user.inquiry.model.dao.InquiryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryService {

    private final InquiryMapper inquiryMapper;

    @Autowired
    public InquiryService(InquiryMapper inquiryMapper) {
        this.inquiryMapper = inquiryMapper;
    }

//    public List<InquiryDTO> findAllInquiry() {
//        return InquiryMapper.findAllInquiry();
//    }

    public InquiryDTO findInquiryByCode(int code) {
        return inquiryMapper.findInquiryByCode(code);
    }

    public List<InquiryDTO> findAllInquiry(Object memberCode) {
        return inquiryMapper.findAllInquiry(memberCode);
    }
}
