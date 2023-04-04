package com.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // 声明当前项目为eureka服务
public class EureKaServer8762Application {
  public static void main(String[] args) {
    SpringApplication.run(EureKaServer8762Application.class, args);
  }
}
