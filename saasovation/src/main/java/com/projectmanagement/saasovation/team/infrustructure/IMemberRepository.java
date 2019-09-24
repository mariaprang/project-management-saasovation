package com.projectmanagement.saasovation.team.infrustructure;

import com.projectmanagement.saasovation.team.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMemberRepository extends JpaRepository<Member, Long> {

    public Member findMemberById(Long id);

    public Member findMemberByUsername(String username);

}
