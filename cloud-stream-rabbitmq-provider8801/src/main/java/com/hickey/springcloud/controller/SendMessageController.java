package com.hickey.springcloud.controller;

import com.hickey.springcloud.service.impl.SendMessageImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@RestController
public class SendMessageController {
    @Resource
    private SendMessageImpl sendMessage;

    @RequestMapping(value = "/send")
    public String sendMessage() {
        //调用接口发送消息
        return sendMessage.send("测试");
    }
}
