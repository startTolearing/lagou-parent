package com.example;

import com.example.pojo.Resume;
import com.example.service.ResumeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class ApplicationTest {
  @Autowired
  private ResumeService resumeService;
  @Test
  public void ResumeServiceImplTest01() {
    Resume resume = resumeService.findDefaultResumeByUserId(2195320L);
    System.out.println(resume);
  }


}
