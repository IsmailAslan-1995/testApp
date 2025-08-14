package com.project.testApp.model.dto.response.generalResponse;

import org.springframework.http.HttpStatus;

public class SuccessStatus {
    private SuccessStatus() {}
    public static Status getSuccessStatus() {
        return new Status(HttpStatus.OK, "No error");
    }
}
