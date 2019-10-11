package com.projectmanagement.saasovation.team.application;

import com.projectmanagement.saasovation.project.domain.Project;
import com.projectmanagement.saasovation.project.infrastructure.ProjectRepository;
import com.projectmanagement.saasovation.saasovation_common.data.InitialData;
import com.projectmanagement.saasovation.team.domain.Member;
import com.projectmanagement.saasovation.team.domain.Team;
import com.projectmanagement.saasovation.team.infrustructure.repositories.member.MemberRepository;
import com.projectmanagement.saasovation.team.infrustructure.repositories.team.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TeamRepository teamRepository;

    private static final Logger log = LoggerFactory.getLogger(MemberController.class);


    @PostMapping("/newMemberForProject/{id}")
    public String createTeam(@PathVariable("id") long id,
                             @RequestParam("fullName") String fullName,
                             @RequestParam("teamName") String teamName,
                             Model model) throws Exception {
        String[] nameCredentials = fullName.split(" ");
        Member member = memberRepository.loadMemberByFullName(nameCredentials[0], nameCredentials[1]);
        if (member == null) {
            member = memberRepository.loadMemberByFullName(nameCredentials[1], nameCredentials[0]);
        }
        if (member == null) {
            model.addAttribute("errorMessage", "Member with these credentials doesn't exist!");
            log.info("ERROR DISPLAYED!!!!!");
            return "project";
        } else {


            Project project = projectRepository.findProjectById(id);
            Team team = new Team(teamName, project);
            team.addTeamMember(member);
            member.addTeam(team);
            teamRepository.saveTeam(team);

            model.addAttribute("project", project);
            Set<Member> projectMembers = new HashSet<>();
            model.addAttribute("teams", project.getTeams());

            for (Team teamchik : project.getTeams()) {
                for (Member mem : teamchik.getTeamMembers()) {
                    projectMembers.add(mem);
                }
            }
            model.addAttribute("allMembers", projectMembers);
        }

        return "project";
    }

}
