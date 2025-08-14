package com.project.testApp.controller;

import com.project.testApp.model.dto.response.generalResponse.GeneralResponseWithData;
import com.project.testApp.model.dto.response.generalResponse.SuccessStatus;
import com.project.testApp.model.dto.response.testCase.GetTestCaseStep;
import com.project.testApp.service.TestCaseStepService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test-case-steps")
public class TestCaseStepController {

    private final TestCaseStepService testCaseStepService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<GeneralResponseWithData<List<GetTestCaseStep>>> getAll(@RequestParam Integer testCaseId) {

        List<GetTestCaseStep> data = testCaseStepService.getStepsByTestCaseId(testCaseId);
        return ResponseEntity.ok(new GeneralResponseWithData<>(SuccessStatus.getSuccessStatus(), data));
    }
}
