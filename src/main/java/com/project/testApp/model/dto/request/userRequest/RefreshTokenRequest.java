package com.project.testApp.model.dto.request.userRequest;

public record RefreshTokenRequest(
        Long userId,
        String refreshToken
) {}
