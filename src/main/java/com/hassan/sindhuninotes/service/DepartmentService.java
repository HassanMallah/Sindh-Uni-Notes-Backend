package com.hassan.sindhuninotes.service;

import com.hassan.sindhuninotes.entity.Department;
import com.hassan.sindhuninotes.entity.Semester;
import com.hassan.sindhuninotes.repository.DepartmentRepository;
import com.hassan.sindhuninotes.repository.SemesterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final SemesterRepository semesterRepository;
    private final DepartmentRepository departmentRepository;

    public DepartmentService(SemesterRepository semesterRepository, DepartmentRepository departmentRepository) {
        this.semesterRepository = semesterRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public List<Semester> getSemestersByDepartment(Integer departmentId) {
        return semesterRepository.findByDepartmentId(departmentId);
    }
}
