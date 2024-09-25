package com.space.spacesinspace.user.inquiry.model.dao;

import com.space.spacesinspace.common.dto.InquiryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface InquiryMapper {

    List<InquiryDTO> findAllInquiryBy(int memberCode);

    InquiryDTO findInquiryByCode(int code);

    void deleteInquiry(int code);

    void editInquiry(InquiryDTO inquiry);

    void registNewInquiry(InquiryDTO newInquiry);
}
