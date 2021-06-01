package com.zy.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-HYSREIX-SERVICE",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/paymentOk")
     String  payment_ok();

    @GetMapping("/payment/paymenterror")
     String  payment_error();

    @GetMapping("/payment/paymentcircuitBreaker/{id}")
     String  paymentcircuitBreaker(@PathVariable(value = "id")int id);
}
