package com.space.spacesinspace.user.pay.model.dao;

import com.space.spacesinspace.common.dto.MemberDTO;
import com.space.spacesinspace.common.dto.PayDTO;
import com.space.spacesinspace.common.dto.PayDetailDTO;
import com.space.spacesinspace.common.dto.ProductDTO;
import com.space.spacesinspace.user.cart.model.dto.CartDTO;
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

    // 상품페이지에서 구매 버튼을 눌렀을때 넘어가는 상품 정보
    ProductDTO payProgress(int productCode);

    // 상품페이지에서 구매 버튼을 눌렀을때 넘어가는 회원 정보
    MemberDTO payProgressUser(int memberCode);

    // 상품 상세페이지에서 [취소] 버튼을 눌렀을때 먼저 레퍼런스 code를 지우고 아래로
    int deletePayDetailMenu(int payCode);

    // 상품 상세페이지에서 [취소] 버튼을 눌렀을때 삭제하고 리스트로 이동
    int deletePayMenu(int payCode);


}
