package com.hickey.springcloud.controller;

import com.hickey.springcloud.domain.CommonResult;
import com.hickey.springcloud.domain.Order;
import com.hickey.springcloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order){

        orderService.create(order);

        return new CommonResult(200,"创建订单成功");
    }
}
