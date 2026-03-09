package com.hassan.sindhuninotes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class MaterialDTO {
    private Integer    id;
    private String     title;
    private String     date;
    private BigDecimal sizeMB;
    private String     fileUrl;
}
