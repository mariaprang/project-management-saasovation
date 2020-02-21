package com.projectmanagement.saasovation.team.application;

import com.projectmanagement.saasovation.saasovation_common.data.InitialData;
import com.projectmanagement.saasovation.team.domain.Member;
import com.projectmanagement.saasovation.team.domain.Role;
import com.projectmanagement.saasovation.team.infrustructure.repositories.member.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberRegistration {

    private static final Logger log = LoggerFactory.getLogger(MemberRegistration.class);

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/register-new-member")
    public String registeraNewMember(@RequestParam("firstName")String firstName,
                                    @RequestParam("lastName") String lastName,
                                    @RequestParam("email") String email,
                                    @RequestParam("password") String password,
                                     @RequestParam("passwordRepeat") String repeatPassword  ) throws Exception {
        log.info("----------------------------------------I'm HERE ----------------------------------------");
        Member member = new Member(firstName, lastName, email, passwordEncoder.encode(password), Role.USER);
        log.info("---------------------------------------- HERE ----------------------------------------"+member.toString());

        Member member2 =
                new Member("TEST-USER", "Prangishvili",
                        "abcdef@email.com", passwordEncoder.encode("test"), Role.USER);
        memberRepository.saveMember(member2);

        memberRepository.saveMember(member);

        return "redirect:/login";
    }
}
