package com.projectmanagement.saasovation.team.infrustructure.validation;

import com.projectmanagement.saasovation.team.domain.Member;

public interface IMemberValidator {

    public boolean memberIsValid(Member member);
}
