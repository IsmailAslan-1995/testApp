package com.project.testApp.service;

import com.project.testApp.model.dto.response.testCase.GetAllTestCases;
import com.project.testApp.model.dto.response.testCase.TestCaseSummary;
import com.project.testApp.model.repository.TestCaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.project.testApp.model.mapper.TestCaseMapper.toDtoList;
import static com.project.testApp.service.ServiceText.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestCaseService {

    private final TestCaseRepository testCaseRepository;

    @Transactional(readOnly = true)
    public List<GetAllTestCases> getAllByPlatform(Integer platformId) {
        try {
            log.info(TEST_CASE_SERVICE_LOG, platformId);
            List<GetAllTestCases> testCases = toDtoList(testCaseRepository.findByPlatform_PlatformId(platformId));
            log.info(TEST_CASE_FETCH_SUCCESS_LOG, platformId);
            return testCases;
        } catch (Exception e) {
            log.error(TEST_CASE_SERVICE_ERROR_LOG, e.getMessage());
            throw new RuntimeException(TEST_CASE_SERVICE_ERROR_LOG, e);
        }
    }

    @Transactional(readOnly = true)
    public List<TestCaseSummary> getTestCaseSummariesByPlatform(Integer platformId) {
        try {
            log.info(TEST_CASE_SUMMARY_FETCH_LOG, platformId);
            List<TestCaseSummary> summaries = testCaseRepository.findSummariesByPlatformId(platformId);
            log.info(TEST_CASE_SUMMARY_FETCH_SUCCESS_LOG, platformId);
            return summaries;
        } catch (Exception e) {
            log.error(TEST_CASE_SERVICE_ERROR_LOG, e.getMessage());
            throw new RuntimeException(TEST_CASE_SERVICE_ERROR_LOG, e);
        }
    }
}
