package com.hassan.sindhuninotes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DepartmentInfoDTO {
    private String name;
    private Integer totalSubjects;
    private Integer totalFiles;
}