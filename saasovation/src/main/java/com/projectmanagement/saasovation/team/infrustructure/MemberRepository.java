package com.projectmanagement.saasovation.team.infrustructure;

import com.projectmanagement.saasovation.team.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberRepository {

    @Autowired
    private IMemberRepository memberRepository;

    public boolean saveMember(Member memberToSave){
        memberRepository.save(memberToSave);
    }

}
