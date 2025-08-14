package com.project.testApp.model.dto.response.testCase;

import lombok.Builder;

@Builder
public record GetAllAction(Integer actionId, String actionName) {}
