package com.hassan.sindhuninotes.service;

import com.hassan.sindhuninotes.dto.SubjectDTO;
import com.hassan.sindhuninotes.dto.SubjectsResponseDTO;
import com.hassan.sindhuninotes.entity.Department;
import com.hassan.sindhuninotes.entity.Material;
import com.hassan.sindhuninotes.entity.Semester;
import com.hassan.sindhuninotes.entity.Subject;
import com.hassan.sindhuninotes.repository.DepartmentRepository;
import com.hassan.sindhuninotes.repository.MaterialRepository;
import com.hassan.sindhuninotes.repository.SemesterRepository;
import com.hassan.sindhuninotes.repository.SubjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    private final DepartmentRepository departmentRepository;
    private final SemesterRepository semesterRepository;
    private final SubjectRepository subjectRepository;
    private final MaterialRepository materialRepository;

    public SubjectService(DepartmentRepository departmentRepository, SemesterRepository semesterRepository, SubjectRepository subjectRepository, MaterialRepository materialRepository) {
        this.departmentRepository = departmentRepository;
        this.semesterRepository = semesterRepository;
        this.subjectRepository = subjectRepository;
        this.materialRepository = materialRepository;
    }

    public SubjectsResponseDTO getSubjectsForSemester(Integer deptId, Integer semNumber) {

        Department dept = departmentRepository.findById(deptId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Department not found: " + deptId));

        Semester sem = semesterRepository.findByDepartmentIdAndNumber(deptId, semNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Semester " + semNumber + " not found for dept " + deptId));

        List<Subject> subjects = subjectRepository.findBySemesterIdOrderById(sem.getId());

        List<SubjectDTO> subjectDTOs = subjects.stream().map(subj -> {
            int notesCount  = materialRepository.countBySubjectIdAndType(subj.getId(), "notes");
            int papersCount = materialRepository.countBySubjectIdAndType(subj.getId(), "past_paper");
            return new SubjectDTO(subj.getId(), subj.getName(), subj.getCode(), notesCount, papersCount);
        }).collect(Collectors.toList());

        int totalFiles = subjectDTOs.stream()
                .mapToInt(s -> s.getNotesCount() + s.getPapersCount())
                .sum();

        return new SubjectsResponseDTO(
                dept.getName(), sem.getNumber(), sem.getSubtitle(), totalFiles, 0, subjectDTOs
        );
    }

    public List<Map<String, Object>> getSubjectsList(Integer deptId) {

        departmentRepository.findById(deptId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Department not found: " + deptId));

        return semesterRepository.findByDepartmentIdOrderByNumberAsc(deptId).stream()
                .flatMap(sem -> subjectRepository.findBySemesterIdOrderById(sem.getId()).stream())
                .map(subj -> {
                    Map<String, Object> item = new LinkedHashMap<>();
                    item.put("id",   subj.getId());
                    item.put("name", subj.getName());
                    return item;
                })
                .collect(Collectors.toList());
    }
}

