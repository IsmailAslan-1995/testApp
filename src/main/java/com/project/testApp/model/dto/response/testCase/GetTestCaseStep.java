package com.project.testApp.model.dto.response.testCase;

import lombok.Builder;

@Builder
public record GetTestCaseStep(
        Integer testCaseStepId,
        Integer testCaseId,
        String stepName,
        String elementName,
        String actionName,
        String inputValue,
        String expectedResult
) {}
