package com.nhnacademy.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfig {

//    @Bean
//    public ServerCodecConfigurer serverCodecConfigurer() {
//        return ServerCodecConfigurer.create();
//    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
