package com.hassan.sindhuninotes.service;


import com.hassan.sindhuninotes.dto.DepartmentInfoDTO;
import com.hassan.sindhuninotes.dto.SemesterDTO;
import com.hassan.sindhuninotes.dto.SemestersResponseDTO;
import com.hassan.sindhuninotes.entity.Department;
import com.hassan.sindhuninotes.entity.Semester;
import com.hassan.sindhuninotes.entity.Subject;
import com.hassan.sindhuninotes.repository.DepartmentRepository;
import com.hassan.sindhuninotes.repository.MaterialRepository;
import com.hassan.sindhuninotes.repository.SemesterRepository;
import com.hassan.sindhuninotes.repository.SubjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SemesterService {

    private final SubjectRepository subjectRepository;
    private final DepartmentRepository departmentRepository;
    private final SemesterRepository semesterRepository;
    private final MaterialRepository materialRepository;

    public SemesterService(SubjectRepository subjectRepository, DepartmentRepository departmentRepository, SemesterRepository semesterRepository, MaterialRepository materialRepository) {
        this.subjectRepository = subjectRepository;
        this.departmentRepository = departmentRepository;
        this.semesterRepository = semesterRepository;
        this.materialRepository = materialRepository;
    }

    public SemestersResponseDTO getSemestersForDept(Integer deptId) {

        Department dept = departmentRepository.findById(deptId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Department not found: " + deptId));

        List<Semester> semesters = semesterRepository.findByDepartmentIdOrderByNumberAsc(deptId);

        List<SemesterDTO> semDTOs = semesters.stream().map(sem -> {

            int subjectCount = subjectRepository.countBySemesterId(sem.getId());

            // fileCount = total materials across all subjects in this semester
            List<Subject> subjects = subjectRepository.findBySemesterIdOrderById(sem.getId());
            int fileCount = subjects.stream()
                    .mapToInt(s -> materialRepository.findBySubjectId(s.getId()).size())
                    .sum();

            return new SemesterDTO(sem.getNumber(), sem.getSubtitle(), subjectCount, fileCount, 0);

        }).collect(Collectors.toList());

        DepartmentInfoDTO deptInfo = new DepartmentInfoDTO(
                dept.getName(),
                dept.getSubjectCount(),
                dept.getFileCount()
        );

        return new SemestersResponseDTO(deptInfo, semDTOs);
    }
}
