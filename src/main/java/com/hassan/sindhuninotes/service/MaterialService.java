package com.hassan.sindhuninotes.service;

import com.hassan.sindhuninotes.dto.MaterialDTO;
import com.hassan.sindhuninotes.dto.MaterialsResponseDTO;
import com.hassan.sindhuninotes.dto.SubjectInfoDTO;
import com.hassan.sindhuninotes.entity.Department;
import com.hassan.sindhuninotes.entity.Material;
import com.hassan.sindhuninotes.entity.Semester;
import com.hassan.sindhuninotes.entity.Subject;
import com.hassan.sindhuninotes.repository.DepartmentRepository;
import com.hassan.sindhuninotes.repository.MaterialRepository;
import com.hassan.sindhuninotes.repository.SemesterRepository;
import com.hassan.sindhuninotes.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MaterialService {


    private final DepartmentRepository  departmentRepository;
    private final SemesterRepository semesterRepository;
    private final SubjectRepository subjectRepository;
    private final MaterialRepository materialRepository;

    public MaterialService(DepartmentRepository departmentRepository, SemesterRepository semesterRepository, SubjectRepository subjectRepository, MaterialRepository materialRepository) {
        this.departmentRepository = departmentRepository;
        this.semesterRepository = semesterRepository;
        this.subjectRepository = subjectRepository;
        this.materialRepository = materialRepository;
    }

    public MaterialsResponseDTO getMaterialsForSubject(Integer deptId, Integer semNumber, Integer subjectId) {

        Department dept = departmentRepository.findById(deptId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Department not found: " + deptId));

        Semester sem = semesterRepository.findByDepartmentIdAndNumber(deptId, semNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Semester not found"));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Subject not found: " + subjectId));

        List<Material> allMaterials = materialRepository.findBySubjectId(subjectId);

        List<MaterialDTO> notes = allMaterials.stream()
                .filter(m -> "notes".equals(m.getType()))
                .map(this::toMaterialDTO)
                .collect(Collectors.toList());

        List<MaterialDTO> papers = allMaterials.stream()
                .filter(m -> "past_paper".equals(m.getType()))
                .map(this::toMaterialDTO)
                .collect(Collectors.toList());

        return new MaterialsResponseDTO(
                dept.getName(),
                sem.getNumber(),
                new SubjectInfoDTO(subject.getName(), subject.getCode()),
                notes,
                papers
        );
    }

    public List<Map<String, Object>> getRecentFiles() {
        return materialRepository.findTop6ByOrderByUploadedAtDesc().stream().map(m -> {
            Subject    subj = m.getSubject();
            Semester   sem  = subj.getSemester();
            Department dept = sem.getDepartment();

            Map<String, Object> item = new LinkedHashMap<>();
            item.put("dept",     dept.getName());
            item.put("semester", sem.getNumber());
            item.put("subject",  subj.getName());
            item.put("type",     "notes".equals(m.getType()) ? "Lecture Notes" : "Past Paper");
            item.put("fileUrl",  "https://drive.google.com/uc?export=download&id=" + m.getFileId());
            return item;
        }).collect(Collectors.toList());
    }

    private MaterialDTO toMaterialDTO(Material m) {
        String fileUrl = "https://drive.google.com/uc?export=download&id=" + m.getFileId();
        String date    = m.getUploadedAt() != null
                ? m.getUploadedAt().toLocalDate().toString()
                : null;
        return new MaterialDTO(m.getId(), m.getTitle(), date, m.getFileSizeMb(), fileUrl);
    }
}