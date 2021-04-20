package com.hickey.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.hickey.springcloud.dao"})
public class MyBatisConfig {
}
