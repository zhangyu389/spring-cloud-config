package com.zy.springcloud.controller;


import com.zy.springcloud.entities.CommonResult;
import com.zy.springcloud.entities.Payment;
import com.zy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPotr;

    @GetMapping("/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") long id){
        Payment paymentById = paymentService.getPaymentById(id);
         log.info("查询成功！");
        return  new CommonResult<Payment>(200,"获取单个成功，端口号:"+serverPotr,paymentById);
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

}
