package com.hassan.sindhuninotes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MaterialsResponseDTO {
    private String department;
    private Short semester;
    private SubjectInfoDTO subject;
    private List<MaterialDTO> notes;
    private List<MaterialDTO> papers;
}
