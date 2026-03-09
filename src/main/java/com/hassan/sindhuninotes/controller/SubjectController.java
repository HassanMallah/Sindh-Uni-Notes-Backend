package com.hassan.sindhuninotes.controller;

import com.hassan.sindhuninotes.dto.SubjectsResponseDTO;
import com.hassan.sindhuninotes.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

   private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<SubjectsResponseDTO> getSubjects(
            @RequestParam Integer dept,
            @RequestParam Integer sem) {
        return ResponseEntity.ok(subjectService.getSubjectsForSemester(dept, sem));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Map<String,Object>>> getSubjectList(@RequestParam Integer dept) {
        return ResponseEntity.ok(subjectService.getSubjectsList(dept));
    }
}