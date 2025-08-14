package com.project.testApp.model.dto.response.generalResponse;

import org.springframework.http.HttpStatus;

public record Status(HttpStatus code, String description) {}
