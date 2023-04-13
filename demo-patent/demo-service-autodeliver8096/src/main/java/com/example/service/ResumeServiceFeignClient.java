package com.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


//@RequestMapping("/resume") 有回退逻辑时，添加此注解可能报错，所以将 /resume 添加到 FeignClient 的 path中
@FeignClient(name = "demo-service-resume", fallback = ResumeFallback.class, path = "/resume")
public interface ResumeServiceFeignClient {

  @GetMapping("/openstate/{userId}")
  public Integer findDefaultResumeState(@PathVariable("userId") Long userId);

}
