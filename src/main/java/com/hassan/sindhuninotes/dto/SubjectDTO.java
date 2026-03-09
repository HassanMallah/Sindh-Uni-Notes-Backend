package com.hassan.sindhuninotes.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubjectDTO {
    private Integer id;
    private String  name;
    private String  code;
    private Integer notesCount;
    private Integer papersCount;
}
