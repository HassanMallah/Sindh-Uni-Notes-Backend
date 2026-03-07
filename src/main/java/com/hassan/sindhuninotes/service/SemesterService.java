package com.hassan.sindhuninotes.service;


import com.hassan.sindhuninotes.entity.Subject;
import com.hassan.sindhuninotes.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterService {

    private final SubjectRepository subjectRepository;

    public SemesterService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getSubjectsBySemester(Integer semesterId) {

        return subjectRepository.findBySemesterId(semesterId);
    }
}
