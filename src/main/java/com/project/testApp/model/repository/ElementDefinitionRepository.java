package com.project.testApp.model.repository;

import com.project.testApp.model.entity.ElementDefinition;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ElementDefinitionRepository extends JpaRepository<ElementDefinition, Integer> {

    @EntityGraph(attributePaths = "locator")
    List<ElementDefinition> findByPlatform_PlatformId(Integer platformId);
}
