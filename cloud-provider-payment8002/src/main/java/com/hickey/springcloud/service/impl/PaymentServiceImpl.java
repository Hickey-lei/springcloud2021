package com.hickey.springcloud.service.impl;

import com.hickey.springcloud.dao.PaymentDao;
import com.hickey.springcloud.entities.Payment;
import com.hickey.springcloud.service.PaymentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    public Payment getpaymentById(@Param("id")Long id){
        return paymentDao.getpaymentById(id);
    }
}
