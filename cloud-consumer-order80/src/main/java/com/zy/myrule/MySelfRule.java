package com.zy.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MySelfRule {

    @Bean
    public IRule Myrule(){

        log.info("正在使用随机的方式");
        return new RandomRule();
    }

}
