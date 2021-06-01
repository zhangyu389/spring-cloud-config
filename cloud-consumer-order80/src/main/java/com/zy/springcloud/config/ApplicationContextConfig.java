package com.zy.springcloud.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class ApplicationContextConfig {

    @Bean
   // @LoadBalanced //提供负载均衡的能力给RestTemplate 默认是轮询
    public RestTemplate getRestTemplate(){

        return new RestTemplate();
    }
}
