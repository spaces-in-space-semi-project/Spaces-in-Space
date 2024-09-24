package com.space.spacesinspace.user.pay.model.dao;

import com.space.spacesinspace.common.dto.PayDTO;
import com.space.spacesinspace.common.dto.PayDetailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PayMapper {

    // 사용자 아이디의 주문내역 조회
    List<PayDTO> findPayList(String loggedInUsername);

    // 사용자 아이디의 주문내역 중 코드로 상세 조회
    PayDetailDTO findPayDetail(int payCode);

    // 관리자가 모든 주문의 전체내역 조회
    List<PayDTO> showPayList();

    // 관리자가 모든 주문의 전체내역중에서, 코드로 상세 조회
    PayDetailDTO findAdminPayDetail(int payCode);
}
