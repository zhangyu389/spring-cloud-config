package com.zy.springcloud.controller;

import com.zy.springcloud.entities.CommonResult;
import com.zy.springcloud.entities.Payment;
import com.zy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPotr;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") long id){
        Payment paymentById = paymentService.getPaymentById(id);
         log.info("查询成功！");
        return  new CommonResult<Payment>(200,"获取单个成功,端口号:"+serverPotr,paymentById);
    }

    @PostMapping("/save")
    public CommonResult<Payment> save(@RequestBody Payment payment){
        int save = paymentService.save(payment);
        if(save==0){
            return new CommonResult<Payment>(-200,"增加失败");
        }
        log.info("插入成功--------"+save);
        return new CommonResult(200,"增加成功",save);
    }


    @GetMapping("/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String  emp:services) {
          log.info("结果是:"+emp);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance: instances) {
            log.info("instance："+instance.getHost()+"\t端口:"+instance.getPort()+"\t地址:"+instance.getUri());


        }
        return this.discoveryClient;
    }

    @GetMapping("/getPaymentByIdTimeOut/{id}")
    public String getPaymentByIdTimeOut(@PathVariable("id") long id){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "AA";
    }


}
