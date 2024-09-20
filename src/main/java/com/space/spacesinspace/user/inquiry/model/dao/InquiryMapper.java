package com.space.spacesinspace.user.inquiry.model.dao;

import com.space.spacesinspace.common.dto.InquiryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InquiryMapper {

    static List<InquiryDTO> findAllInquiry() {
        return null;
    }

    InquiryDTO findInquiryByCode(int code);
}
