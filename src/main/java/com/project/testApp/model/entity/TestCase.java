package com.project.testApp.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "test_case")
@Data
public class TestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_case_id")
    private Integer testCaseId;

    @Column(name = "test_name", length = 1000, nullable = false)
    private String testName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_id")
    private Platform platform;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    private ModuleType module;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "is_smoke")
    private Boolean isSmoke;

    @Column(name = "is_run")
    private Boolean isRun;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
