package com.project.testApp.service;

import com.project.testApp.model.dto.response.testCase.GetTestCaseStep;
import com.project.testApp.model.repository.TestCaseStepRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.project.testApp.service.ServiceText.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestCaseStepService {

    private final TestCaseStepRepository testCaseStepRepository;

    @Transactional(readOnly = true)
    public List<GetTestCaseStep> getStepsByTestCaseId(Integer testCaseId) {
        try {
            log.info(TEST_CASE_STEP_SERVICE_LOG, testCaseId);
            List<GetTestCaseStep> steps = testCaseStepRepository.findStepsByTestCaseId(testCaseId);
            log.info(TEST_CASE_STEP_FETCH_SUCCESS_LOG, testCaseId);
            return steps;
        } catch (Exception e) {
            log.error(TEST_CASE_STEP_SERVICE_ERROR_LOG, e.getMessage());
            throw new RuntimeException(TEST_CASE_STEP_SERVICE_ERROR_LOG, e);
        }
    }
}
