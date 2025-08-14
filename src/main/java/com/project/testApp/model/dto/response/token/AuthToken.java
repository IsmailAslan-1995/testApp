package com.project.testApp.model.dto.response.token;

public record AuthToken(
        String accessToken,
        String refreshToken,
        long expiresIn
) {}
