package com.projectmanagement.saasovation.project.domain;

import com.projectmanagement.saasovation.board.domain.Board;
import com.projectmanagement.saasovation.team.domain.BaseEntity;
import com.projectmanagement.saasovation.team.domain.Member;
import com.projectmanagement.saasovation.team.domain.Team;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Indexed
@Table(name = "project")
public class Project extends BaseEntity <Long> {

    @Field(termVector = TermVector.YES)
    @Column(name = "pr_name", nullable = false, unique = false)
    private String projectName;

    @IndexedEmbedded
    @ManyToOne
    @JoinColumn(name = "project_owner_id")
    private Member projectOwner;

    @Field(termVector = TermVector.YES)
    @Column(name = "pr_type", nullable = false, unique = false)
    private String projectType;

    @OneToMany(mappedBy = "project")
    private Set <Team> teams;

    @OneToMany(mappedBy = "project")
    private Set <Board> boards;

    public Project() {
        super();
    }

    public Project(String projectName, Member projectOwner, String projectType) {
        super();
        this.projectOwner = projectOwner;
        this.projectName = projectName;
        this.projectType = projectType;
        this.teams = new HashSet <>();
    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Member getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(Member projectOwner) {
        this.projectOwner = projectOwner;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public Set <Team> getTeams() {
        return teams;
    }

    /**
     * methods typical for aggregate object
     **/
    public boolean addTeamToProject(Team team) {
        return this.teams.add(team);
    }

    public boolean removeTeamFromProject(Team team) {
        return this.teams.remove(team);
    }

    public Team checkIfExists(String teamType) {
        for (Team team : this.getTeams()) {
            if (team.getTeamName().equalsIgnoreCase(teamType)) {
                return team;
            }
        }
        return null;
    }

    public boolean addBoard(Board board) {
        return boards.add(board);
    }

    /**
     * ------------------------------------------
     **/
    @Override
    public boolean equals(Object toCompareWith) {
        boolean isEqual = false;
        if (toCompareWith != null && this.getClass() == toCompareWith.getClass()) {
            Project typedObject = (Project) toCompareWith;
            isEqual = this.getId().equals(typedObject.getId()) &&
                    this.getProjectOwner().equals(typedObject.getProjectOwner());
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        int hashCodeValue =
                +(71121 * 79)
                        + this.getId().hashCode()
                        + this.getProjectOwner().hashCode();
        return hashCodeValue;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectName='" + projectName + '\'' +
                ", projectOwner=" + projectOwner.toString() +
                ", projectType='" + projectType + '\'' +
                '}';
    }

    public boolean checkIFValidOwner(Member member) {
        if (member.equals(this.projectOwner)) {
            return true;
        } else return false;
    }
}
