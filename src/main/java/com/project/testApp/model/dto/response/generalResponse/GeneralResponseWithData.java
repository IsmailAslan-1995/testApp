package com.project.testApp.model.dto.response.generalResponse;

public record GeneralResponseWithData<T>(Status status, T data) {}
