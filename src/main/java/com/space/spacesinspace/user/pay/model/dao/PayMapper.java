package com.space.spacesinspace.user.pay.model.dao;

import com.space.spacesinspace.common.dto.PayDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PayMapper {


    List<PayDTO> findPayList();

    PayDTO findPayDetail(int payCode);
}
