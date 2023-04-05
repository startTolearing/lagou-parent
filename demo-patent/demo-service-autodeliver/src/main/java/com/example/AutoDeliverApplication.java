package com.example;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletRegistration;

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

  /**
   * 在被监控的微服务中注册一个servlet，后期我们就是通过访问这个servlet来获取该微服务的Hystrix监控数据的
   * 前提：被监控的微服务需要引入Spring Boot的actuator功能。
   * @return
   */
  @Bean
  public ServletRegistrationBean getServlet() {
    HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
    ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
    registrationBean.setLoadOnStartup(1);
    registrationBean.addUrlMappings("/actuator/hystrix.stream");
    registrationBean.setName("HystrixMetricsStreamServlet");
    return registrationBean;
  }

}
