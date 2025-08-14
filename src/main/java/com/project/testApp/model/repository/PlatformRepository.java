package com.project.testApp.model.repository;

import com.project.testApp.model.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlatformRepository extends JpaRepository<Platform, Integer> {
    List<Platform> findAllByOrderByPlatformNameAsc();
}
