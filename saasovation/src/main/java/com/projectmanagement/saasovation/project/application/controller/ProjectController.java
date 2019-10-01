package com.projectmanagement.saasovation.project.application.controller;

import com.projectmanagement.saasovation.project.domain.Project;
import com.projectmanagement.saasovation.project.infrastructure.ProjectRepository;
import com.projectmanagement.saasovation.saasovation_common.data.InitialData;
import com.projectmanagement.saasovation.team.domain.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProjectController {

    private static final Logger log = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    ProjectRepository projectRepository;


    @PostMapping(value = {"/projects/{id}"})
    public String getProjectView(@PathVariable("id") long id, Model model) {
        Project project = projectRepository.findProjectById(id);
        log.info("PROJECT INFO HERE!!!!: "+project.toString());
        return "project";
    }
}
