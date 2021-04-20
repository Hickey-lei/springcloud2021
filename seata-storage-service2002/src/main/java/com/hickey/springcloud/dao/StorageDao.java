package com.hickey.springcloud.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface StorageDao {
    //扣减库存
    public void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
