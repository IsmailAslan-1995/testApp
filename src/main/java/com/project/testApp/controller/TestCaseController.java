package com.project.testApp.controller;

import com.project.testApp.model.dto.response.generalResponse.GeneralResponseWithData;
import com.project.testApp.model.dto.response.generalResponse.SuccessStatus;
import com.project.testApp.model.dto.response.testCase.GetAllTestCases;
import com.project.testApp.model.dto.response.testCase.TestCaseSummary;
import com.project.testApp.service.TestCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test-cases")
public class TestCaseController {

    private final TestCaseService testCaseService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<GeneralResponseWithData<List<GetAllTestCases>>> getAllTestCases(@RequestParam Integer platformId) {
        List<GetAllTestCases> data = testCaseService.getAllByPlatform(platformId);
        return ResponseEntity.ok(new GeneralResponseWithData<>(SuccessStatus.getSuccessStatus(), data));
    }

    @GetMapping("/summary")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<GeneralResponseWithData<List<TestCaseSummary>>> getTestCaseSummaries(
            @RequestParam Integer platformId) {
        List<TestCaseSummary> data = testCaseService.getTestCaseSummariesByPlatform(platformId);
        return ResponseEntity.ok(new GeneralResponseWithData<>(SuccessStatus.getSuccessStatus(), data));
    }

}
