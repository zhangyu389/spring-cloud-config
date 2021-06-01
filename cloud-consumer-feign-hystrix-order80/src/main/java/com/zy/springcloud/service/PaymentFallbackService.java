package com.zy.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements  PaymentHystrixService {
    @Override
    public String payment_ok() {
        return "服务降级处理！payment_ok";
    }

    @Override
    public String payment_error() {
        return "服务降级处理！payment_error";
    }

    @Override
    public String paymentcircuitBreaker(int id) {
        return "服务降级paymentcircuitBreaker";
    }
}
