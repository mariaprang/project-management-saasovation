package com.projectmanagement.saasovation.team.domain;

import com.projectmanagement.saasovation.project.domain.Project;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Team extends BaseEntity <Long> {

    private String teamName;

    @ManyToMany
    @JoinTable(
            name = "team_members",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id"))
    private Set <Member> teamMembers;


    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public Team() {
    }

    public Team(String teamName, Project project) {
        this.teamName = teamName;
        this.project = project;
        this.teamMembers = new HashSet <>();
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

    /**
     * methods typical for aggregate object
     **/

    public boolean addTeamMember(Member member) {
        return teamMembers.add(member);
    }

    public boolean removeTeamMember(Member member) {
        return teamMembers.remove(member);
    }

    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * --------------------------------------------------
     */

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
//        Team team = (Team) o;
//        return this.getId() == team.getId() && this.teamName.equalsIgnoreCase(team.teamName);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return this.getTeamName().equalsIgnoreCase(team.getTeamName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getId(), teamName);
    }
}
