package com.projectmanagement.saasovation.project.application.controller;

import com.projectmanagement.saasovation.project.application.service.ProjectSearchService;
import com.projectmanagement.saasovation.project.domain.Project;
import com.projectmanagement.saasovation.project.infrastructure.ProjectRepository;
import com.projectmanagement.saasovation.team.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectSearchService projectSearchService;

    @RequestMapping(value = {"/index","/"}, method = {RequestMethod.GET})
    public String getUserAccount(Model model) {
        Member currentUser = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("member", currentUser);
        model.addAttribute("member_id", currentUser.getId());
        model.addAttribute("first_name", currentUser.getFirstName());
        model.addAttribute("last_name", currentUser.getLastName());

        List<Project> projectList = projectRepository.findAllProjects();
        model.addAttribute("projects", projectList);
        return "index";
    }

    @RequestMapping(value = "/projects", method = {RequestMethod.GET})
    public String getAllProjects(Model model){
        List<Project> projectList = projectRepository.findAllProjects();
        model.addAttribute("projects", projectList);
        return "index";
    }

    @GetMapping(value = "/searchProjectsBy")
    public String respondToSearchQuery(@RequestParam(value="searchTerm",required=true) String searchTerm, Model model){
        List<Project> projectList = projectSearchService.fuzzySearchProjects(searchTerm);
        Member currentUser = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("member", currentUser);
        model.addAttribute("member_id", currentUser.getId());
        model.addAttribute("first_name", currentUser.getFirstName());
        model.addAttribute("last_name", currentUser.getLastName());

        model.addAttribute("projects", projectList);
        return "index";
    }



    @RequestMapping(value = "/user")
    public Principal user(Principal principal) {
        return principal;
    }
}
