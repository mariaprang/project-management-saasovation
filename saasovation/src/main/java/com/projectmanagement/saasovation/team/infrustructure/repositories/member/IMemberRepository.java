package com.projectmanagement.saasovation.team.infrustructure.repositories.member;

import com.projectmanagement.saasovation.team.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IMemberRepository extends JpaRepository<Member, Long> {

    public Member findMemberById(Long id);

    public Member findMemberByEmail(String email);

//    @Query("SELECT m FROM member m WHERE LOWER(m.first_name) = LOWER(:firstName) and LOWER(m.last_name) = LOWER(:lastName)")
//    public Member findMemberByFullName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query("SELECT u FROM Member u WHERE u.firstName = ?1 and u.lastName = ?2")
    public Member findMemberByFullName(String firstName, String lastName);
}
