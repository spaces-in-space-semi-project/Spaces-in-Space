package com.space.spacesinspace.user.inquiry.model.dao;

import com.space.spacesinspace.common.dto.InquiryDTO;
import com.space.spacesinspace.common.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InquiryMapper {

    List<InquiryDTO> findAllInquiryBy(int memberCode);

    InquiryDTO findInquiryByCode(int inquiryCode);

    int deleteInquiry(int code);

    int registNewInquiry(InquiryDTO newInquiry);

    int updateInquiry(InquiryDTO inquiry);

    ReplyDTO findReplyByCode(int inquiryCode);
}
