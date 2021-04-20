package com.hickey.springcloud.controller;

import com.hickey.springcloud.entities.CommonResult;
import com.hickey.springcloud.entities.Payment;
import com.hickey.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController
{

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String ServerPort;

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
            return new CommonResult(200,"查询成功:"+ServerPort,payment);
        }else{
            return new CommonResult(444,"没有对应记录");
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("*****element:"+service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String lb(){
        log.info("端口号："+ServerPort);
        return ServerPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        return "hi ,i'am paymentZipkin server fall back ,welcome to shanghai";
    }

}
