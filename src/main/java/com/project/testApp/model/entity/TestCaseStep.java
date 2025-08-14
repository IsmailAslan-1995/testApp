package com.project.testApp.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "test_case_step")
@Data
public class TestCaseStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_case_step_id")
    private Integer testCaseStepId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_case_id", nullable = false)
    private TestCase testCase;

    @Column(name = "step_name", nullable = false)
    private String stepName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "element_definition_id")
    private ElementDefinition elementDefinition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_id")
    private Action action;

    @Column(name = "input_value", columnDefinition = "TEXT")
    private String inputValue;

    @Column(name = "expected_result", columnDefinition = "TEXT")
    private String expectedResult;
}
