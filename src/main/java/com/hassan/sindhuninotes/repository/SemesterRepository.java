package com.hassan.sindhuninotes.repository;

import com.hassan.sindhuninotes.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SemesterRepository extends JpaRepository<Semester, Integer> {

    List<Semester> findByDepartmentIdOrderByNumberAsc(Integer departmentId);
    Optional<Semester> findByDepartmentIdAndNumber(Integer departmentId, Integer number);
}
