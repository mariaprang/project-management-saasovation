package com.projectmanagement.saasovation.team.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Team extends BaseEntity<Long> {

    private String teamName;

    @ManyToMany
    @JoinTable(
            name = "team_members",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id"))
    private Set<Member> teamMembers;


    public Team(){ }

    public Team(String teamName){
        this.teamName = teamName;
        this.teamMembers = new HashSet <>();
    }

    public boolean addTeamMember(Member teamMember){
        return teamMembers.add(teamMember);
    }

    public Set <Member> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(Set <Member> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Team team = (Team) o;
        return this.getId()==team.getId() && this.teamName.equalsIgnoreCase(team.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getId(), teamName);
    }
}
