package com.example.service;

import org.springframework.stereotype.Component;

/**
 * 熔断器的回退逻辑需要继承对应的接口即可
 * 还需要把该类注入到IOC容器中
 */
@Component
public class ResumeFallback implements ResumeServiceFeignClient{
  @Override
  public Integer findDefaultResumeState(Long userId) {
    return -100;
  }
}
