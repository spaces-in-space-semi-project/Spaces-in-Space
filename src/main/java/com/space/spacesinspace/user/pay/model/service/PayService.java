package com.space.spacesinspace.user.pay.model.service;

import com.space.spacesinspace.common.dto.PayDTO;
import com.space.spacesinspace.user.pay.model.dao.PayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayService {


    public final PayMapper payMapper;

    @Autowired
    public PayService(PayMapper payMapper) {
        this.payMapper = payMapper;
    }

    public List<PayDTO> findPayList() {
        return payMapper.findPayList();
    }

    public PayDTO findPayDetail(int payCode) { return payMapper.findPayDetail(payCode); }

}
