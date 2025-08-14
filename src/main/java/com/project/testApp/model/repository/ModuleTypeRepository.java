package com.project.testApp.model.repository;

import com.project.testApp.model.entity.ModuleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleTypeRepository extends JpaRepository<ModuleType, Integer> {
    List<ModuleType> findAllByOrderByModuleNameAsc();
}
