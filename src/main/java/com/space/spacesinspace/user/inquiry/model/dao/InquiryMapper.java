package com.space.spacesinspace.user.inquiry.model.dao;

import com.space.spacesinspace.common.dto.InquiryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface InquiryMapper {

    List<InquiryDTO> findAllInquiryBy(int memberCode);

    InquiryDTO findInquiryByCode(int inquiryCode);

    @Transactional
    void deleteInquiry(int code);

    @Transactional
    void registNewInquiry(InquiryDTO newInquiry);

    @Transactional
    int updateInquiry(InquiryDTO inquiry);
}
