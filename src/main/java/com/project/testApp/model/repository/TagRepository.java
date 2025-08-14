package com.project.testApp.model.repository;

import com.project.testApp.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    List<Tag> findAllByOrderByTagNameAsc();
}
