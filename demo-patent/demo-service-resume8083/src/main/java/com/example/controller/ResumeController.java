package com.example.controller;

import com.example.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

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
    return port;
  }

}
