package com.hassan.sindhuninotes.controller;

import com.hassan.sindhuninotes.entity.Material;
import com.hassan.sindhuninotes.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

   private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/{id}/materials")
    public ResponseEntity<List<Material>> getMaterialsBySubject(@PathVariable Integer id) {
        return ResponseEntity.ok(subjectService.getMaterialsBySubject(id));
    }
}