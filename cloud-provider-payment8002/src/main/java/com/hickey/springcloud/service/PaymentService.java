package com.hickey.springcloud.service;

import com.hickey.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    public int create(Payment payment);

    public Payment getpaymentById(@Param("id")Long id);
}
