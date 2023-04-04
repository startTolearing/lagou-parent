package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EntityScan("com.example.pojo")
@SpringBootApplication
//@EnableEurekaClient       // 开启eureka客户端 --- eureka独有
@EnableDiscoveryClient    // 开启注册中心客户端，（通用性注解，eureka，nacos都可以）
public class ResumeApplication {
  public static void main(String[] args) {
    SpringApplication.run(ResumeApplication.class, args);
  }
}
