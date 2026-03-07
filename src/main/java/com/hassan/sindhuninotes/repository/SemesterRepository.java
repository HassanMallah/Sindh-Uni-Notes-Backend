package com.hassan.sindhuninotes.repository;

import com.hassan.sindhuninotes.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SemesterRepository extends JpaRepository<Semester, Integer> {

    List<Semester> findByDepartmentId(Integer departmentId);
}
