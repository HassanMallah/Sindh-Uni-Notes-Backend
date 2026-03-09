package com.hassan.sindhuninotes.repository;

import com.hassan.sindhuninotes.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    List<Subject> findBySemesterIdOrderById(Integer semesterId);
    Integer countBySemesterId(Integer semesterId);
}
