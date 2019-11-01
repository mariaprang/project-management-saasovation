package com.projectmanagement.saasovation.team.application;

import com.projectmanagement.saasovation.team.domain.Member;
import com.projectmanagement.saasovation.team.domain.Role;
import com.projectmanagement.saasovation.team.infrustructure.repositories.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class MemberRegistration {

    @Autowired
    private MemberRepository memberRepository;

    @RequestMapping(value = {"/register"})
    public String getRegistrateionPage(){
        return "register-member";
    }

    @RequestMapping(value="/registerNewMember")
    public String registerNewMemebr(@RequestParam("firstName")String firstName,
                                    @RequestParam("lastName") String lastName,
                                    @RequestParam("email") String email,
                                    @RequestParam("password") String password) throws Exception {

        Member member = new Member(firstName, lastName, email, password, Role.USER);
        memberRepository.saveMember(member);
        return "redirect:/login";
    }
}
