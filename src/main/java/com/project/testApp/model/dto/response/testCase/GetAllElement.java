package com.project.testApp.model.dto.response.testCase;

import lombok.Builder;

@Builder
public record GetAllElement(
        Integer elementId,
        String key,
        String value,
        String locatorType
) {}
