package com.hassan.sindhuninotes.repository;

import com.hassan.sindhuninotes.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer>
{
}
