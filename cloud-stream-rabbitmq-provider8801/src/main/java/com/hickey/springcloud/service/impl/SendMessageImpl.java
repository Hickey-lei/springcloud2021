package com.hickey.springcloud.service.impl;

import com.hickey.springcloud.service.SendMessage;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;


@EnableBinding(Source.class) //消息推送者固定注解
public class SendMessageImpl implements SendMessage {

    @Resource //注入指定名字通道 output
    private MessageChannel output;

    @Override
    public String send(String msg) {
        String serial = UUID.randomUUID().toString();
        //发送消息
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("发送者8001发送消息："+serial);
        return null;
    }
}
