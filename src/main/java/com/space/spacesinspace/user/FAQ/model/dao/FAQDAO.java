package com.space.spacesinspace.user.FAQ.model.dao;

import com.space.spacesinspace.common.dto.FAQDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface FAQDAO {

    FAQDTO getFAQByCode(@Param("faqCode") String faqCode);

    List<FAQDTO> getAllFAQs();
}

