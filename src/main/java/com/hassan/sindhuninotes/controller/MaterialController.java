package com.hassan.sindhuninotes.controller;

import com.hassan.sindhuninotes.dto.MaterialsResponseDTO;
import com.hassan.sindhuninotes.service.MaterialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public ResponseEntity<MaterialsResponseDTO> getMaterials(
            @RequestParam Integer dept,
            @RequestParam Integer sem,
            @RequestParam Integer subject) {
        return ResponseEntity.ok(materialService.getMaterialsForSubject(dept, sem, subject));
    }

    @GetMapping("/recent")
    public ResponseEntity<List<Map<String, Object>>> getRecentFiles() {
        return ResponseEntity.ok(materialService.getRecentFiles());
    }
}
