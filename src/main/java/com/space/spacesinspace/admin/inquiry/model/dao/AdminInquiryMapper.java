package com.space.spacesinspace.admin.inquiry.model.dao;

import com.space.spacesinspace.common.dto.InquiryDTO;
import com.space.spacesinspace.common.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminInquiryMapper {

    List<InquiryDTO> findAllInquiry();

    InquiryDTO findInquiryByCode(int inquiryCode);

    void deleteInquiry(int inquiryCode);

    ReplyDTO findReplyByCode(int inquiryCode);

    List<InquiryDTO> findInquiryBySearch(String searchValue);
}
