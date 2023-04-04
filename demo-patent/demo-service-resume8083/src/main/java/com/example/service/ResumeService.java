package com.example.service;

import com.example.pojo.Resume;
import org.springframework.stereotype.Service;

import java.util.Locale;

public interface ResumeService {

  Resume findDefaultResumeByUserId(Long userId);
}
