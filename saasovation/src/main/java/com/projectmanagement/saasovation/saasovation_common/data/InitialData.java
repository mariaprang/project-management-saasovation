package com.projectmanagement.saasovation.saasovation_common.data;

import com.projectmanagement.saasovation.project.domain.Project;
import com.projectmanagement.saasovation.project.domain.ProjectType;
import com.projectmanagement.saasovation.project.infrastructure.ProjectRepository;
import com.projectmanagement.saasovation.team.domain.Member;
import com.projectmanagement.saasovation.team.domain.ProjectOwner;
import com.projectmanagement.saasovation.team.domain.Role;
import com.projectmanagement.saasovation.team.infrustructure.MemberRepository;
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

    @Autowired
    public InitialData(MemberRepository userRepository,
                       ProjectRepository projectRepository,
                       PasswordEncoder passwordEncoder) {
        this.memberRepository = userRepository;
        this.projectRepository = projectRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {

        Member member =
                new Member("Maria", "Prangishvili",
                        "test@email.com", "maria", passwordEncoder.encode("test"), Role.USER);
        ProjectOwner projectOwner = new ProjectOwner("Mike", "Krupskii",
                "mike@email.com", "mike", passwordEncoder.encode("mike"), Role.USER);

        Project project = new Project("Marketing Creatives", projectOwner, ProjectType.Business.getMessage());
        Project project2 = new Project("Travel Service Desk", projectOwner, ProjectType.ServiceDesk.getMessage());

        try {
            memberRepository.saveMember(member);
            memberRepository.saveMember(projectOwner);
            projectRepository.saveProject(project);
            projectRepository.saveProject(project2);
        } catch (Exception ex) {
            log.info(ex.getMessage());
        }
        log.info("USER ID: " + member.getId());

    }

}
