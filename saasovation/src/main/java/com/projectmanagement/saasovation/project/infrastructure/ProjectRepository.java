package com.projectmanagement.saasovation.project.infrastructure;

import com.projectmanagement.saasovation.project.domain.Project;
import com.projectmanagement.saasovation.project.infrastructure.validation.ProjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepository {

    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private ProjectValidator projectValidator;

    public boolean saveProject(Project project) {
        if (projectValidator.projectIsValid(project)) {
            projectRepository.save(project);
            return true;
        } else {
            return false;
        }
    }

    public List<Project> findAllProjects(){
        return projectRepository.findAll();
    }


}
