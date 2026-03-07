package com.hassan.sindhuninotes.service;

import com.hassan.sindhuninotes.entity.Material;
import com.hassan.sindhuninotes.repository.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final MaterialRepository materialRepository;

    public SubjectService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<Material> getMaterialsBySubject(Integer subjectId) {
        return materialRepository.findBySubjectId(subjectId);
    }
}

