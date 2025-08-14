package com.project.testApp.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "locator")
@Data
public class Locator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locator_id")
    private Integer locatorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;

    @Column(name = "locator_name", nullable = false)
    private String locatorName;
}
