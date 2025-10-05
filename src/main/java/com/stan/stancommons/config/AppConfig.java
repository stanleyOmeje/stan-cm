package com.stan.stancommons.config;

import com.systemspecs.remita.extended.utils.RedisUtility;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@EnableJpaRepositories(basePackages = {"com.systemspecs.remita.vending.vendingcommon.repository","com.systemspecs.remita.vending.vendingcommon.flight.repository"})
@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public RedisUtility redisUtility(){
        return new RedisUtility();
    }
}
