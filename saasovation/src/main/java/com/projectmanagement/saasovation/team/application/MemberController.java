package com.projectmanagement.saasovation.team.application;

import com.projectmanagement.saasovation.project.domain.Project;
import com.projectmanagement.saasovation.project.infrastructure.ProjectRepository;
import com.projectmanagement.saasovation.team.domain.Member;
import com.projectmanagement.saasovation.team.domain.Role;
import com.projectmanagement.saasovation.team.domain.Team;
import com.projectmanagement.saasovation.team.infrustructure.repositories.member.MemberRepository;
import com.projectmanagement.saasovation.team.infrustructure.repositories.team.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        String[] memberCredentials = fullName.split(" ");

        Member member = memberRepository.loadMemberByFullName(memberCredentials[0], memberCredentials[1]);
        Project project = projectRepository.findProjectById(id);
        model.addAttribute("project", project);

        if (member != null) {
            /* A method being called from the ProjectAggregate class*/
            Team team = project.checkIfExists(teamName);
            if (team == null) {
                team = new Team(teamName, project);
            }
            team.addTeamMember(member);
            member.addTeam(team);
            teamRepository.saveTeam(team);

            Set <Member> projectMembers = new HashSet <>();
            model.addAttribute("teams", project.getTeams());

            for (Team teamchik : project.getTeams()) {
                for (Member mem : teamchik.getTeamMembers()) {
                    projectMembers.add(mem);
                }
            }
            model.addAttribute("allMembers", projectMembers);

            return "redirect:/projects/" + id;
        } else {
            model.addAttribute("errorMessage", "Member with these credentials doesn't exist!");
            return "project";
        }

    }

//    @RequestMapping(value = "/registerNewMember")
//    public String registerNewMembers(@RequestParam("firstName") String firstName,
//                                     @RequestParam("lastName") String lastName,
//                                     @RequestParam("email") String email,
//                                     @RequestParam("password") String password,
//                                     @RequestParam("passwordRepeat") String passwordRepeat,
//                                     Model model) throws Exception {
//
//      //  if (password.equals(passwordRepeat)) {
//            Member member = new Member(firstName, lastName, email, password, Role.USER);
//            log.info("****************************************************** PRINTING********************************" + member.toString());
//            memberRepository.saveMember(member);
//            return "redirect:/login";
//       // } else {
//       //     // TODO: add an error to model
//         //   model.addAttribute("errorMessage", "Password didn't match!");
//       //     return "register";
//       // }
//
//    }

    @GetMapping("/register")
    public String getRegistrationPage() {
        return "register-member";
    }

}
