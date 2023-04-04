package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@EnableHystrix      // 打开Hystrix功能
@EnableCircuitBreaker // 开启熔断器功能
@EnableDiscoveryClient
@SpringBootApplication


// @SpringCloudApplication  // 综合行注解 @SpringCloudApplication = @SpringBootApplication + @EnableDiscoveryClient + @EnableCircuitBreaker
public class AutoDeliverApplication {
  public static void main(String[] args) {
    SpringApplication.run(AutoDeliverApplication.class, args);
  }

  @Bean
  @LoadBalanced   // 负载均衡
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }
}
