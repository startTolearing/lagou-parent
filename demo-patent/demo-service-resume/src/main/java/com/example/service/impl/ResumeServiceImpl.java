package com.example.service.impl;

import com.example.dao.ResumeDao;
import com.example.pojo.Resume;
import com.example.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResumeServiceImpl implements ResumeService {

  @Autowired
  private ResumeDao resumeDao;

  @Override
  public Resume findDefaultResumeByUserId(Long userId) {
//    Resume resume = new Resume();
//    resume.setId(userId);
//    Example<Resume> example = Example.of(resume);
//
//    Optional<Resume> result = resumeDao.findOne(example);

//    return result.get();
    return resumeDao.findById(userId).get();
  }
}
