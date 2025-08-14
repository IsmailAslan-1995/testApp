package com.project.testApp.model.repository;

import com.project.testApp.model.entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ActionRepository extends JpaRepository<Action, Integer> {

    List<Action> findByPlatform_PlatformId(Integer platformId);
}
