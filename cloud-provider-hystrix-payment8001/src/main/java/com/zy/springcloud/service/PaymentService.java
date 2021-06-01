package com.zy.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {


    public  String payment_ok(){


      return "当前线程的名字:"+Thread.currentThread().getName();
    }

    @HystrixCommand(fallbackMethod = "payment_errorHandler" ,commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })//设置调用超时时间的峰值，超时则会调用设置的paymentInfo_TimeOutHandler方法
    public  String payment_error(){
         int timeout=5;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "当前线程的名字:"+Thread.currentThread().getName()+"  延时时间:"+timeout;
    }

    private String payment_errorHandler(){

        return "抱歉!程序出现问题，请稍后再试。";
  }

    //  服务熔断
    @HystrixCommand(fallbackMethod = "paymentcircuitfallback",commandProperties ={
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否启用断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少后跳闸
    } )
    public String paymentcircuitBreaker(@PathVariable(value = "id")int id){

        if(id<0){
            throw new RuntimeException("id不能为空");
        }

        String orderNumber= IdUtil.simpleUUID();//hutool工具
        return "调用成功，支付订单号是:"+orderNumber;
    }

    public String paymentcircuitfallback(@PathVariable(value = "id")int id){

        return "id不能为负数哦！";
    }
}
