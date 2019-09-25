package com.projectmanagement.saasovation.project.infrastructure.validation;

import com.projectmanagement.saasovation.project.domain.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectValidator implements IProjectValidator {

    //TODO: implement this
    @Override
    public boolean projectIsValid(Project project) {
        return true;
    }
}


