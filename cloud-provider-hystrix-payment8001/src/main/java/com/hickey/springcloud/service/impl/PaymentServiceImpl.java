package com.hickey.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.hickey.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池"+Thread.currentThread().getName()+" paymentInfo_OK，id "+id+"\t"+"O(∩_∩)O哈哈~";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池"+Thread.currentThread().getName()+" paymentInfo_TimeOut，id "+id+"\t"+"O(∩_∩)O哈哈~  耗时（秒）"+timeNumber;
    }

    @HystrixCommand(fallbackMethod ="PaymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路由
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少后跳闸
    })
    public String PaymentCircuitBreaker(@PathVariable("id")Integer id){
        if(id<0){
            throw new RuntimeException("*****id 不能是负数");
        }else{
            String uuid = IdUtil.simpleUUID();
            return Thread.currentThread().getName()+"\t"+"调用成功 流水号："+uuid;
        }
    }
    public String PaymentCircuitBreaker_fallback(@PathVariable("id")Integer id){
        return "id 不能是负数，请稍后再试，o(╥﹏╥)o"+"id:"+id;
    }

}
