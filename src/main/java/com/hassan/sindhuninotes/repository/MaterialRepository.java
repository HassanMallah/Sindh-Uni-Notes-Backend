package com.hassan.sindhuninotes.repository;

import com.hassan.sindhuninotes.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
    List<Material> findBySubjectId(Integer subjectId);
    Integer countBySubjectIdAndType(Integer subjectId, String type);
    List<Material> findTop6ByOrderByUploadedAtDesc();
}
