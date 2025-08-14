package com.project.testApp.model.repository;

import com.project.testApp.model.dto.response.testCase.GetTestCaseStep;
import com.project.testApp.model.entity.TestCaseStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestCaseStepRepository extends JpaRepository<TestCaseStep, Integer> {

    @Query("""
        select new com.project.testApp.model.dto.response.testCase.GetTestCaseStep(
            s.testCaseStepId,
            s.testCase.testCaseId,
            s.stepName,
            ed.elementName,
            a.actionName,
            s.inputValue,
            s.expectedResult
        )
        from TestCaseStep s
        left join s.elementDefinition ed
        left join s.action a
        where s.testCase.testCaseId = :testCaseId
        order by s.testCaseStepId asc
        """)
    List<GetTestCaseStep> findStepsByTestCaseId(Integer testCaseId);
}
