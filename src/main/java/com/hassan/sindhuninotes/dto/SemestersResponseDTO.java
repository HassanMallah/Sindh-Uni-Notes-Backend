package com.hassan.sindhuninotes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SemestersResponseDTO {
    private DepartmentInfoDTO department;
    private List<SemesterDTO> semesters;
}
