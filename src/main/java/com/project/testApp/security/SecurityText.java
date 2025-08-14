package com.project.testApp.security;

final class SecurityText {
    private SecurityText() {
    }

    static final String UNAUTHORIZED_ACCESS_LOG = "Unauthorized access attempt: {}";
    static final String JWT_TOKEN_EXTRACTION_LOG = "Extracting JWT token from request";
    static final String JWT_TOKEN_VALIDATION_LOG = "Validating JWT token: {}";
    static final String USER_FOUND_LOG = "User found with ID: {}";
    static final String USER_AUTHENTICATION_LOG = "Setting authentication for user: {}";
    static final String JWT_TOKEN_ERROR_LOG = "Error in JwtAuthenticationFilter: {}";
    static final String JWT_TOKEN_LOG = "JWT Token: {}";
    static final String JWT_TOKEN_GENERATION_LOG = "Generating JWT token for user ID: {}";
    static final String JWT_TOKEN_VALIDATION_ERROR_LOG = "JWT token validation error: {}";
    static final String JWT_TOKEN_USER_ID_LOG = "Extracted user ID from JWT: {}";
    static final String JWT_USER_DETAILS_CREATE_LOG = "Creating JwtUserDetails for user: {}";
}
