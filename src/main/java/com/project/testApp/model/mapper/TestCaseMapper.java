package com.project.testApp.model.mapper;

import com.project.testApp.model.dto.response.testCase.GetAllTestCases;
import com.project.testApp.model.entity.TestCase;

import java.util.List;
import java.util.function.Function;

import static com.project.testApp.model.mapper.MapperGeneric.toDtoListGeneric;

public final class TestCaseMapper {

    private TestCaseMapper() {}

    private static final Function<TestCase, GetAllTestCases> testCaseToDtoMapper = e -> GetAllTestCases.builder()
            .testCaseId(e.getTestCaseId())
            .testName(e.getTestName())
            .platformId(e.getPlatform().getPlatformId())
            .ModuleId(e.getModule().getModuleId())
            .status(e.getStatus())
            .isSmoke(e.getIsSmoke())
            .isRun(e.getIsRun())
            .tagId(e.getTag().getTagId())
            .build();

    public static List<GetAllTestCases> toDtoList(List<TestCase> list) {
        return toDtoListGeneric(list, testCaseToDtoMapper);
    }
}
