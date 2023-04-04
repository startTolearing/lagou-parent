package com.example.dao;

import com.example.pojo.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ResumeDao extends JpaRepository<Resume, Long> {
}
