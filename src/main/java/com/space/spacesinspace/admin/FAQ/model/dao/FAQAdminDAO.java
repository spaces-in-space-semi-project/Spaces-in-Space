package com.space.spacesinspace.admin.FAQ.model.dao;

import com.space.spacesinspace.common.dto.FAQDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FAQAdminDAO {

    FAQDTO getFAQByCode(@Param("faqCode") String faqCode);

    List<FAQDTO> getAllFAQs();

    void insertFAQ(FAQDTO faq);

    void updateFAQ(FAQDTO faq);

    void deleteFAQ(@Param("faqCode") String faqCode);
}
