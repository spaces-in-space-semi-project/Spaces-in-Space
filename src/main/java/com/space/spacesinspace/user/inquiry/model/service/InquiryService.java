package com.space.spacesinspace.user.inquiry.model.service;

import com.space.spacesinspace.common.dto.InquiryDTO;
import com.space.spacesinspace.user.inquiry.model.dao.InquiryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public InquiryDTO findInquiryByCode(int code) {
        return inquiryMapper.findInquiryByCode();
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
    public InquiryDTO updateInquiry(InquiryDTO newInquiry) {
        Optional<InquiryDTO> optionalInquiryDTO = Optional.ofNullable(inquiryMapper.findInquiryByCode());

        if (optionalInquiryDTO.isPresent()) {
            InquiryDTO originInquiry = optionalInquiryDTO.get();

            originInquiry.setInquiryTitle(newInquiry.getInquiryTitle());

            originInquiry.setInquiryDetail(newInquiry.getInquiryDetail());

        } else {
            return newInquiry;
        }
        return newInquiry;
    }
}
