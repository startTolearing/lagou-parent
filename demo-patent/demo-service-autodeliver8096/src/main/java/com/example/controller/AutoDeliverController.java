package com.example.controller;

import com.example.service.ResumeServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autodeliver")
public class AutoDeliverController {

  @Autowired
  private ResumeServiceFeignClient resumeServiceFeignClient;

  @GetMapping("/checkState/{userId}")
  public Integer findResumeOpenState(@PathVariable Long userId) {
    Integer defaultResumeState = resumeServiceFeignClient.findDefaultResumeState(userId);
    return defaultResumeState;
  }
}
