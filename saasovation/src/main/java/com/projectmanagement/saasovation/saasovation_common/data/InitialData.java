package com.projectmanagement.saasovation.saasovation_common.data;

import com.projectmanagement.saasovation.project.domain.Project;
import com.projectmanagement.saasovation.project.domain.ProjectType;
import com.projectmanagement.saasovation.project.infrastructure.ProjectRepository;
import com.projectmanagement.saasovation.team.domain.Member;
import com.projectmanagement.saasovation.team.domain.Role;
import com.projectmanagement.saasovation.team.domain.Team;
import com.projectmanagement.saasovation.team.infrustructure.repositories.member.MemberRepository;
import com.projectmanagement.saasovation.team.infrustructure.repositories.team.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@Configuration
public class InitialData {

    private static final Logger log = LoggerFactory.getLogger(InitialData.class);

    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final PasswordEncoder passwordEncoder;
    private final TeamRepository teamRepository;

    @Autowired
    public InitialData(MemberRepository userRepository,
                       ProjectRepository projectRepository,
                       TeamRepository teamRepository,
                       PasswordEncoder passwordEncoder) {
        this.memberRepository = userRepository;
        this.projectRepository = projectRepository;
        this.passwordEncoder = passwordEncoder;
        this.teamRepository = teamRepository;
    }

    @PostConstruct
    public void init() {

        Member member =
                new Member("Maria", "Prangishvili",
                        "test@email.com", passwordEncoder.encode("test"), Role.USER);
        Member member2 =
                new Member("Mike", "Krupskii",
                        "test2@email.com", passwordEncoder.encode("test2"), Role.USER);
        Member member3 =
                new Member("Tom", "Johnson",
                        "test3@email.com", passwordEncoder.encode("test3"), Role.USER);
        Member member4 =
                new Member("Johannes", "Tompson",
                        "test4@email.com", passwordEncoder.encode("test4"), Role.USER);

        Project project = new Project("Marketing Creatives", member, ProjectType.Business.getMessage());


//        Team team = new Team("Team ONE", project);
//        team.addTeamMember(member);
//        team.addTeamMember(member2);
//        team.addTeamMember(member3);
//        team.addTeamMember(member4);
//        team.setProject(project);
//        project.addTeamToProject(team);

        Project project2 = new Project("Travel Service Desk", member, ProjectType.ServiceDesk.getMessage());
        Project project3 = new Project("Teams in Space", member2, ProjectType.Software.getMessage());

        try {
            memberRepository.saveMember(member);
            memberRepository.saveMember(member2);
            memberRepository.saveMember(member3);
            memberRepository.saveMember(member4);
            projectRepository.saveProject(project2);
            projectRepository.saveProject(project3);
            projectRepository.saveProject(project);

           // teamRepository.saveTeam(team);
        } catch (Exception ex) {
            log.info(ex.getMessage());
        }
        log.info("ALL PROJECTS: " +  projectRepository.findAllProjects().toString());

    }

}
