package com.projectmanagement.saasovation.project.infrastructure.validation;

import com.projectmanagement.saasovation.project.domain.Project;

public interface IProjectValidator {

    public boolean projectIsValid(Project project);
}
