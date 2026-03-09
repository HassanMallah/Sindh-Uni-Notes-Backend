package com.hassan.sindhuninotes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SemesterDTO {
    private Short number;
    private String  subtitle;
    private Integer subjectCount;
    private Integer fileCount;
    private Integer coverage;

}