package com.project.testApp.model.dto.response.testCase;

import lombok.Builder;

@Builder
public record GetAllPlatform(Integer platformId, String platformName) {
}
