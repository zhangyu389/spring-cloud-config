package com.zy.springcloud.controller;

import com.zy.springcloud.entities.CommonResult;
import com.zy.springcloud.entities.Payment;
import com.zy.springcloud.lb.LoadBalanced;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
  private final static String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";

  @Resource
  private RestTemplate restTemplate;

  @Resource
  private LoadBalanced loadBalanced;

  @Resource
  private DiscoveryClient discoveryClient;

  @GetMapping("/consumer/payment/save")
  public CommonResult<Payment> save( Payment payment){

      return restTemplate.postForObject(PAYMENT_URL+"/payment/save",payment,CommonResult.class);
  }

  @GetMapping("/consumer/payment/getPaymentById/{id}")
  public CommonResult<Payment> getPaymentById(@PathVariable("id") long id){

      return restTemplate.getForObject(PAYMENT_URL+"/payment/getPaymentById/"+id,CommonResult.class);
  }

  @GetMapping("/consumer/paymentLB")
  public  String getPaymentLB(){
    List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

    if (null==instances||instances.size()<=0){
        return null;
    }
    ServiceInstance instances1 = loadBalanced.instances(instances);
    URI uri = instances1.getUri();
    log.info("URI :"+uri);
    return null;
  }

}
