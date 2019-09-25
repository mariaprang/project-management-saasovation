package com.projectmanagement.saasovation.team.infrustructure.validation;


import com.projectmanagement.saasovation.team.domain.Member;
import org.springframework.stereotype.Service;

@Service
public class MemberValidator implements IMemberValidator {

    //TODO: implement this
    @Override
    public boolean memberIsValid(Member member) {
        return true;
    }
}
