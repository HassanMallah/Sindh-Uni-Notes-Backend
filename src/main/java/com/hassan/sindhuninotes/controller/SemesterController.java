package com.hassan.sindhuninotes.controller;

import com.hassan.sindhuninotes.dto.SemestersResponseDTO;
import com.hassan.sindhuninotes.service.SemesterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/semesters")
public class SemesterController {

    private final SemesterService semesterService;

    public SemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }


    @GetMapping
    public ResponseEntity<SemestersResponseDTO> getSemesters(@RequestParam Integer dept) {
        return ResponseEntity.ok(semesterService.getSemestersForDept(dept));
    }
}
