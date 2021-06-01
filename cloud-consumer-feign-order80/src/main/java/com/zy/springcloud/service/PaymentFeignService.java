package com.zy.springcloud.service;

import com.zy.springcloud.entities.CommonResult;
import com.zy.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping("/payment/getPaymentById/{id}")
     CommonResult<Payment> getPaymentById(@PathVariable("id") long id);

    @GetMapping("/payment/getPaymentByIdTimeOut/{id}")
    String getPaymentByIdTimeOut(@PathVariable("id") long id);
}
