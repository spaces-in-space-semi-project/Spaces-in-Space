package com.space.spacesinspace.user.pay.model.service;

import com.space.spacesinspace.common.dto.MemberDTO;
import com.space.spacesinspace.common.dto.PayDTO;
import com.space.spacesinspace.common.dto.PayDetailDTO;
import com.space.spacesinspace.common.dto.ProductDTO;
import com.space.spacesinspace.user.pay.model.dao.PayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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
        if (loggedInUsername != null) {
            return payMapper.findPayList(loggedInUsername);  // 로그인된 사용자 ID로 쿼리 실행
        } else {
            return new ArrayList<>();  // 로그인이 안 된 경우 빈 리스트 반환
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

    public List<PayDTO> showPayList() {
        return payMapper.showPayList();
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
}
