package com.project.testApp.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "element_definition")
@Data
public class ElementDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "element_definition_id")
    private Integer elementDefinitionId;

    @Column(name = "element_name", nullable = false)
    private String elementName;

    @Column(name = "value", nullable = false, columnDefinition = "TEXT")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locator_id", nullable = false)
    private Locator locator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;
}
