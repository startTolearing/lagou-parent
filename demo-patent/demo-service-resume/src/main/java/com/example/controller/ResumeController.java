package com.example.controller;

import com.example.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/resume")
public class ResumeController {
  @Autowired
  private ResumeService resumeService;

  @Value("${server.port}")
  private Integer port;

  @GetMapping("/openstate/{userId}")
  public Integer findDefaultResumeState(@PathVariable Long userId) {
//    return resumeService.findDefaultResumeByUserId(userId).getIsOpenResume();
    // 模拟处理超时
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return port;
  }

}
