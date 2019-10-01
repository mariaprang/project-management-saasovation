package com.projectmanagement.saasovation.project.application.controller;

import com.projectmanagement.saasovation.project.domain.Project;
import com.projectmanagement.saasovation.project.infrastructure.ProjectRepository;
import com.projectmanagement.saasovation.team.domain.Member;
import com.projectmanagement.saasovation.team.domain.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class ProjectController {

    private static final Logger log = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    ProjectRepository projectRepository;


    @PostMapping(value = {"/projects/{id}"})
    public String getProjectView(@PathVariable("id") long id, Model model) {
        Project project = projectRepository.findProjectById(id);
        model.addAttribute("project", project);
        Set <Member> projectMembers = new HashSet <>();
        model.addAttribute("teams", project.getTeams());

        log.info("---------------------------------------");
        log.info("TEAM: " + project.getTeams());
        log.info("---------------------------------------");

        for (Team team : project.getTeams()) {
            for (Member member : team.getTeamMembers()) {
                projectMembers.add(member);
                log.info("---------------------------------------");
                log.info("MEMBER: " + member.toString());
                log.info("---------------------------------------");
            }
        }

        model.addAttribute("allMembers", projectMembers);
        return "project";
    }
}
