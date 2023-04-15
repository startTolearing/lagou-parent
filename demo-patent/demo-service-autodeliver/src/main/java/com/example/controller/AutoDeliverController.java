package com.example.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/autodeliver")
public class AutoDeliverController {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private DiscoveryClient discoveryClient;

  /*@GetMapping("/checkState/{userId}")
  public Integer findResumeOpenState(@PathVariable Long userId) {
    // http://localhost:8080/resume/openstate/2195320
    Integer status = restTemplate.
        getForObject("http://localhost:8080/resume/openstate/" + userId, Integer.class);
    System.out.println("======>>>调⽤简历微服务，获取到⽤户" + userId + "的默认简历当前状态为：" + status);
    return status;
  }*/


  /**
   * 将服务注册到eureka注册中心之后的调用
   */
  /*@GetMapping("/checkState/{userId}")
  public Integer findResumeOpenState(@PathVariable Long userId) {
    // http://localhost:8080/resume/openstate/2195320

    // TODO 从注册中心获取我们关注的服务的实例信息和接口
    // 1、获取实例信息，使用客户端获取DiscoverClient
    List<ServiceInstance> instances = discoveryClient.getInstances("demo-service-resume");
    // 2、从中选取一个实例即可 --- 负载均衡
    ServiceInstance serviceInstance = instances.get(0);
    // 主机号--IP
    String host = serviceInstance.getHost();
    // 端口号
    int port = serviceInstance.getPort();
    String url = "http://" + host + ":" + port + "/resume/openstate/" + userId;

    Integer status = restTemplate.
        getForObject(url, Integer.class);
    System.out.println("======>>>调⽤简历微服务，获取到⽤户" + userId + "的默认简历当前状态为：" + status);
    return status;
  }*/

  /**
   * 使用ribbon的负载均衡后的调用
   */
  @GetMapping("/checkState/{userId}")
  public Integer findResumeOpenState(@PathVariable Long userId) {
    // http://localhost:8080/resume/openstate/2195320
    String url = "http://demo-service-resume/resume/openstate/" + userId;
    Integer status = restTemplate.
        getForObject(url, Integer.class);
    System.out.println("======>>>调⽤简历微服务，获取到⽤户" + userId + "的默认简历当前状态为：" + status);
    return status;
  }

  /**
   * 提供者模拟调用超时，调用者方法添加Hystrix表示
   * 使用commandProperties熔断的细节
   */
  @HystrixCommand(
      // 线程池的唯一标识，不唯一的话则会共用
      threadPoolKey = "findResumeOpenStateTimeout",
      // 对线程池的配置
      threadPoolProperties = {
          @HystrixProperty(name = "coreSize", value = "2"),
          @HystrixProperty(name = "maxQueueSize", value = "20")
      },

      commandProperties = {
          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
      }
  )
  @GetMapping("/checkStateTimeout/{userId}")
  public Integer findResumeOpenStateTimeout(@PathVariable Long userId) {
    String url = "http://demo-service-resume/resume/openstate/" + userId;

    Integer status = restTemplate.
        getForObject(url, Integer.class);
    System.out.println("======>>>调⽤简历微服务，获取到⽤户" + userId + "的默认简历当前状态为：" + status);
    return status;
  }


  @GetMapping("/checkStateTimeoutFallback/{userId}")
  @HystrixCommand(
      threadPoolKey = "findResumeOpenStateTimeoutFallback",
      threadPoolProperties = {
          @HystrixProperty(name = "coreSize", value = "2"),
          @HystrixProperty(name = "maxQueueSize", value = "20")
      },

      commandProperties = {
          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")

          // Hystrix高级配置
          ,@HystrixProperty(name =
          "metrics.rollingStats.timeInMilliseconds",value = "8000"),
          @HystrixProperty(name =
              "circuitBreaker.requestVolumeThreshold",value = "2"),
          @HystrixProperty(name =
              "circuitBreaker.errorThresholdPercentage",value = "50"),
          @HystrixProperty(name =
              "circuitBreaker.sleepWindowInMilliseconds",value = "3000")
      },
      fallbackMethod = "myFallback"     // 服务降级返回的调用默认的方法
  )
  public Integer findResumeOpenStateTimeoutFallback(@PathVariable Long userId) {
    String url = "http://demo-service-resume/resume/openstate/" + userId;
    Integer status = restTemplate.
        getForObject(url, Integer.class);
    System.out.println("======>>>调⽤简历微服务，获取到⽤户" + userId + "的默认简历当前状态为：" + status);
    return status;
  }
  public Integer myFallback(Long userId) {
    return -1;
  }


}
