package com.space.spacesinspace.user.FAQ.model.dao;

import com.space.spacesinspace.user.FAQ.model.dto.FAQDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FAQDAO {

    @Select("SELECT * FROM faq WHERE code = #{code}")
    FAQDTO getFAQByCode(@Param("code") String code);

    @Select("SELECT * FROM faq")
    List<FAQDTO> getAllFAQs();

    @Insert("INSERT INTO faq (code, title, detail) VALUES (#{code}, #{title}, #{detail})")
    void insertFAQ(FAQDTO faq);

    @Update("UPDATE faq SET title = #{title}, detail = #{detail} WHERE code = #{code}")
    void updateFAQ(FAQDTO faq);

    @Delete("DELETE FROM faq WHERE code = #{code}")
    void deleteFAQ(@Param("code") String code);
}
