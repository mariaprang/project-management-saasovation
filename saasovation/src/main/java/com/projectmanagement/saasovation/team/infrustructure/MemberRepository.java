package com.projectmanagement.saasovation.team.infrustructure;

import com.projectmanagement.saasovation.team.domain.Member;
import com.projectmanagement.saasovation.team.infrustructure.validation.MemberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class MemberRepository {

    @Autowired
    private IMemberRepository memberRepository;
    @Autowired
    private MemberValidator validator;

    public boolean saveMember(Member memberToSave) throws Exception {
        if(validator.memberIsValid(memberToSave)){
            memberRepository.save(memberToSave);
            return true;
        }
        else throw new Exception("Invalid user credentials");
    }

    public Member loadUserByUsername(String username){
        return memberRepository.findMemberByUsername(username);
    }



}
