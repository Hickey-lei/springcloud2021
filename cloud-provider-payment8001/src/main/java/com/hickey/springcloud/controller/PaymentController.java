package com.hickey.springcloud.controller;

import com.hickey.springcloud.entities.CommonResult;
import com.hickey.springcloud.entities.Payment;
import com.hickey.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController
{

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment)
    {
        int result = paymentService.create(payment);
        log.info("*****插入结果："+result);
        if(result > 0){
            return new CommonResult(200,"插入数据库库成功",result);
        }else{
            return new CommonResult(444,"插入数据库失败");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getpaymentById(@PathVariable("id")Long id)
    {
        Payment payment = paymentService.getpaymentById(id);
        log.info("*****查询结果："+payment);
        if(payment != null){
            return new CommonResult(200,"查询成功",payment);
        }else{
            return new CommonResult(444,"没有对应记录");
        }
    }
}
