package com.hickey.springcloud.controller;

import com.hickey.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentHystrixController {
    @Resource
    private PaymentService paymentService;

    /*
    * 正常访问
    * */
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id")Integer id) {
        String paymentInfoOk = paymentService.paymentInfo_OK(id);
        log.info("****result"+paymentInfoOk);
        return paymentInfoOk;
    }

    /*
    * 超时访问
    * */
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id")Integer id) {
        String paymentInfoTimeOut = paymentService.paymentInfo_TimeOut(id);
        log.info("***result"+paymentInfoTimeOut);
        return paymentInfoTimeOut;
    }
}
