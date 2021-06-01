package com.zy.springcloud.controller;

import com.zy.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payment/paymentOk")
    public String  payment_ok(){
    String ok=paymentService.payment_ok();
    return ok;
    }


    @GetMapping("/payment/paymenterror")
    public String  payment_error(){
        String error=paymentService.payment_error();
        return error;
    }

    @GetMapping("/payment/paymentcircuitBreaker/{id}")
    public String  paymentcircuitBreaker(@PathVariable(value = "id")int id){
        String Breaker=paymentService.paymentcircuitBreaker(id);
        return Breaker;
    }


}
