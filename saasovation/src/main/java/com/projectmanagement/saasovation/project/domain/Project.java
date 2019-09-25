package com.projectmanagement.saasovation.project.domain;

import com.projectmanagement.saasovation.team.domain.BaseEntity;
import com.projectmanagement.saasovation.team.domain.ProjectOwner;
import com.projectmanagement.saasovation.team.domain.Team;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

public class Project extends BaseEntity<Long> {

    private String projectName;
    private ProjectOwner projectOwner;
    private Team team;


}
