package com.projectmanagement.saasovation.saasovation_common.data;

import com.projectmanagement.saasovation.team.domain.Member;
import com.projectmanagement.saasovation.team.domain.Role;
import com.projectmanagement.saasovation.team.infrustructure.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Configuration
public class InitialData {

    private static final Logger log = LoggerFactory.getLogger(InitialData.class);


    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitialData(MemberRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.memberRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {

        Member user =
                new Member("Maria", "Prangishvili",
                        "test@email.com", "maria", passwordEncoder.encode("test"), Role.USER);
        try {
            memberRepository.saveMember(user);
        } catch (Exception ex) {
            log.info(ex.getMessage());
        }
        log.info("USER ID: "+user.getId());

    }

}
