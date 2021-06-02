package com.zy.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope  //刷新注解标签
public class ConfigController {

    @Value("${config.info}")
    private String dd;

    @GetMapping
    public  String aa(){

        return dd;

    }


}
