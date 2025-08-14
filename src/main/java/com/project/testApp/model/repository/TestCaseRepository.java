package com.project.testApp.model.repository;

import com.project.testApp.model.dto.response.testCase.TestCaseSummary;
import com.project.testApp.model.entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestCaseRepository extends JpaRepository<TestCase, Integer> {
    List<TestCase> findByPlatform_PlatformId(Integer platformId);
    @Query("""
           select new com.project.testApp.model.dto.response.testCase.TestCaseSummary(
               tc.testCaseId, tc.testName
           )
           from TestCase tc
           where tc.platform.platformId = :platformId
           order by tc.testCaseId
           """)
    List<TestCaseSummary> findSummariesByPlatformId(@Param("platformId") Integer platformId);
}

