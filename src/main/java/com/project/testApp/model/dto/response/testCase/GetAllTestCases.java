package com.project.testApp.model.dto.response.testCase;

import lombok.Builder;

@Builder
public record GetAllTestCases(
        Integer testCaseId,
        String testName,
        Integer platformId,
        Integer ModuleId,
        String status,
        Boolean isSmoke,
        Boolean isRun,
        Integer tagId
) {}
