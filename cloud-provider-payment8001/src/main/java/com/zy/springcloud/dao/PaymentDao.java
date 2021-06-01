package com.zy.springcloud.dao;

import com.zy.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentDao {

    int save(Payment payment);

    List all();

    Payment getPaymentById(@Param("id") long id);
}
