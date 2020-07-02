package com.thinking.robot.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Configuration
public class RestTemplateConfig {
    
    @Bean("restTemplate")
    public RestTemplate restTemplate(){
        RestTemplateBuilder builder = new RestTemplateBuilder();
        StringHttpMessageConverter m = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        builder.additionalMessageConverters(m);
        return builder.build();
    }
}
