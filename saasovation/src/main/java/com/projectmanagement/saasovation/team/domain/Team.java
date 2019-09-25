package com.projectmanagement.saasovation.team.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name="team")
public class Team extends BaseEntity<Long> {

   // private Set<Member> teamMembers;

    public Team(){

    }

//    public boolean addTeamMember(Member teamMember){
//        return teamMembers.add(teamMember);
//    }
}
