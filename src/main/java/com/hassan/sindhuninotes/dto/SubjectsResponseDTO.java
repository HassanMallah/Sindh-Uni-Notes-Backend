package com.hassan.sindhuninotes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
public class SubjectsResponseDTO {

    private String department;
    private Short semester;
    private String subtitle;
    private Integer totalFiles;
    private Integer coverage;
    private List<SubjectDTO> subjects;
}
