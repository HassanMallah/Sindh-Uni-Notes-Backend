package com.hassan.sindhuninotes.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;

    @JsonProperty("tags")
    private String tag;

    @Column(name = "subject_count")
    @JsonProperty("subject_count")
    private Integer subjectCount;

    @Column(name = "file_count")
    @JsonProperty("file_count")
    private Integer fileCount;

    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createdAt;

}
