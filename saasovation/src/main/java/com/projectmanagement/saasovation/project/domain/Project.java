package com.projectmanagement.saasovation.project.domain;

import com.projectmanagement.saasovation.team.domain.BaseEntity;
import com.projectmanagement.saasovation.team.domain.ProjectOwner;

import javax.persistence.*;

@Entity
@Table(name = "project")
public class Project extends BaseEntity <Long> {
    /*
     @ManyToOne(
          fetch = FetchType.LAZY,
          optional = false
  )
  @JoinColumn(
          name = "manager_id",
          nullable = false
  )
     */

    @Column(name = "pr_name", nullable = false, unique = false)
    private String projectName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "projectowner_id", referencedColumnName = "id")
    private ProjectOwner projectOwner;

    @Column(name = "pr_type", nullable = false, unique = false)
    private String projectType;
//
//    private Team team;
//
//    private Set <Task> tasks;

    public Project() {
        super();
    }

    public Project(String projectName, ProjectOwner projectOwner, String projectType) {
        super();
        this.projectName = projectName;
        this.projectOwner = projectOwner;
        this.projectType = projectType;
    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ProjectOwner getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(ProjectOwner projectOwner) {
        this.projectOwner = projectOwner;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

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
}
