package com.zy.springcloud;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DruidDataSourceAutoConfigure.class })
@EnableEurekaClient
public class GateWayMain9527 {
     //启动报错的话需要一处web依赖
    public static void main(String[] args) {
        SpringApplication.run(GateWayMain9527.class, args);
    }
}
