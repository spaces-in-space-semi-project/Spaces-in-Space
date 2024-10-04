package com.space.spacesinspace.user.pay.model.service;

import com.space.spacesinspace.common.dto.MemberDTO;
import com.space.spacesinspace.common.dto.PayDTO;
import com.space.spacesinspace.common.dto.PayDetailDTO;
import com.space.spacesinspace.common.dto.ProductDTO;
import com.space.spacesinspace.user.cart.model.dto.CartDTO;
import com.space.spacesinspace.user.pay.model.dao.PayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PayService {


    private PayMapper payMapper;

    @Autowired
    public PayService(PayMapper payMapper) {
        this.payMapper = payMapper;
    }

    public List<PayDTO> findPayList() {

        String loggedInUsername = getLoggedInUsername();  // 로그인된 사용자 ID 가져오기
        List<PayDTO> list = payMapper.findPayList(loggedInUsername);
        if (loggedInUsername != null && list != null && !list.isEmpty()) {
            return list;
        } else {
            return payMapper.findPayListY(loggedInUsername);
        }
    }

    public PayDetailDTO findPayDetail(int payCode) { return payMapper.findPayDetail(payCode); }

    /*================================================================================================*/

    public String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();  // 로그인한 사용자 ID 반환
        }
        return null;  // 로그인이 되어 있지 않으면 null 반환
    }
    /*================================================================================================*/

    /*관리자 주문내역 전체 조회*/
    public List<PayDTO> showPayList() {
        return payMapper.showPayList();
    }

    public List<PayDetailDTO> findPayDetailList(int payCode) { return payMapper.findPayDetailList(payCode);
    }

    public PayDetailDTO findAdminPayDetail(int payCode) {
        return payMapper.findAdminPayDetail(payCode);
    }

    public ProductDTO payProgress(int productCode) {
        return payMapper.payProgress(productCode);
    }

    public MemberDTO payProgressUser(int memberCode) {
        return payMapper.payProgressUser(memberCode);
    }


    @Transactional
    public void deletePayMenu(int payCode) {
        payMapper.deletePayDetailMenu(payCode);
        payMapper.deletePayMenu(payCode);

    }

    @Transactional
    public void deleteAdminPayMenu(int payCode) {
        payMapper.deletePayDetailMenu(payCode);
        payMapper.deletePayMenu(payCode);
    }

    @Transactional
    public void addPayList(PayDTO payDTO) {
        payMapper.addPayList(payDTO);
    }

    @Transactional
    public void addPayDetailList(PayDetailDTO payDetailDTO){
        payMapper.addPayDetailList(payDetailDTO);
    }


    public PayDTO findPayByCode(int payCode) {

        PayDTO payList = payMapper.findPayByCode(payCode);

        if (payList != null) {
            return payList;
        } else {
            return payMapper.findPayByCodeY(payCode);
        }
    }

    public List<CartDTO> findCartList(int memberCode) {
        return payMapper.findCartList(memberCode);
    }

    public List<PayDTO> searchPayList(String searchValue) {
        return payMapper.searchPayList(searchValue);
    }


    public void updatePayMenu(int payCode) {
        payMapper.updatePayMenu(payCode);
    }

    public void updateAdminPayMenu(int payCode) {
        payMapper.updatePayMenu(payCode);
    }

    public void updateAdminDeliverStatus(int payCode) {
        payMapper.updateAdminDeliverStatus(payCode);
    }
}
