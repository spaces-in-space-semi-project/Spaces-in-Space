package com.space.spacesinspace.user.inquiry.model.dao;

import com.space.spacesinspace.common.dto.InquiryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InquiryMapper {

    List<InquiryDTO> findAllInquiryBy(int memberCode);

    InquiryDTO findInquiryByCode();

    void deleteInquiry(int code);

    void registNewInquiry(InquiryDTO newInquiry);

    void updateInquiry(InquiryDTO newInquiry);
}
