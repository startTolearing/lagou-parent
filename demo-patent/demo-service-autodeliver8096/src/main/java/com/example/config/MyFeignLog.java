package com.example.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFeignLog {
  @Bean
  Logger.Level feignLog() {
    return Logger.Level.FULL;
  }
}
