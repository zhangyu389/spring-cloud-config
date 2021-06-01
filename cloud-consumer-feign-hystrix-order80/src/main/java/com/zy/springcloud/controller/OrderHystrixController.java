package com.zy.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sun.xml.internal.bind.v2.model.core.ID;
import com.zy.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "allerrorHandler")
public class OrderHystrixController {

 @Autowired
 private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/paymentOk")
    public String  payment_ok(){
        String ok=paymentHystrixService.payment_ok();
        return ok;
    }

    @HystrixCommand
//    (fallbackMethod = "payment_errorHandler" ,commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })//设置调用超时时间的峰值，超时则会调用设置的paymentInfo_TimeOutHandler方法
    @GetMapping("/consumer/payment/paymenterror")
    public String  payment_error(){
        String error=paymentHystrixService.payment_error();
        return error;
    }

    private String payment_errorHandler(){

        return "抱歉!程序出现问题，请稍后再试,客户端暂时无法访问!";
    }

    private String allerrorHandler(){

        return "抱歉!fuwuqi 异常!";
    }

    @GetMapping("/consumer/payment/paymentcircuitBreaker/{id}")
    public String  paymentcircuitBreaker(@PathVariable(value = "id")int id){
        log.info("id"+id);
        String ok=paymentHystrixService.paymentcircuitBreaker(id);
        return ok;
    }

}
