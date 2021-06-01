package com.zy.springcloud.service;

import com.zy.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaymentService {
    int save(Payment payment);

     List all();

     Payment getPaymentById(@Param("id") long id);
}
