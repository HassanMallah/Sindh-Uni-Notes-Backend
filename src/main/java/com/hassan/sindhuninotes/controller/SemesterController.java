package com.hassan.sindhuninotes.controller;


import com.hassan.sindhuninotes.entity.Subject;
import com.hassan.sindhuninotes.service.DepartmentService;
import com.hassan.sindhuninotes.service.SemesterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/semesters")
public class SemesterController {

    private final SemesterService semesterService;

    public SemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }


    @GetMapping("/{id}/subjects")
    public ResponseEntity<List<Subject>> getSubjectsBySemester(@PathVariable Integer id) {
        return ResponseEntity.ok(semesterService.getSubjectsBySemester(id));
    }
}
