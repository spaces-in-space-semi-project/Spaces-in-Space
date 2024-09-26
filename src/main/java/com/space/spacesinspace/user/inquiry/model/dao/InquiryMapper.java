package com.space.spacesinspace.user.inquiry.model.dao;

import com.space.spacesinspace.common.dto.InquiryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface InquiryMapper {

    List<InquiryDTO> findAllInquiryBy(int memberCode);

    InquiryDTO findInquiryByCode(int inquiryCode);

    void deleteInquiry(int code);

    void registNewInquiry(InquiryDTO newInquiry);

    int updateInquiry(InquiryDTO inquiry);
}
