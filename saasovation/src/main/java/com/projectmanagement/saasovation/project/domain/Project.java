package com.projectmanagement.saasovation.project.domain;

import com.projectmanagement.saasovation.team.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="project")
public class Project extends BaseEntity<Long> {

    private String projectName;

    private Long projectOwnerId;

}
