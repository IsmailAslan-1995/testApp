package com.project.testApp.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "action")
@Data
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "action_id")
    private Integer actionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;

    @Column(name = "action_name", nullable = false)
    private String actionName;
}
