package com.projectmanagement.saasovation.team.domain;

import com.projectmanagement.saasovation.project.domain.Project;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project_owner")
public class ProjectOwner extends Member {

    @Transient
    private Set <Project> projects;

    public ProjectOwner(String firstName, String lastName, String email,
                        String username, String password, Role role) {
        super(firstName, lastName, email, username, password, role);
        projects = new HashSet <>();
    }


    public boolean addProject(Project project) {
        return projects.add(project);
    }

    @Override
    public boolean equals(Object toCompareWith) {
        boolean isEqual = false;
        if (toCompareWith != null && this.getClass() == toCompareWith.getClass()) {
            ProjectOwner typedObject = (ProjectOwner) toCompareWith;
            isEqual = this.getId().equals(typedObject.getId()) &&
                    this.getUsername().equals(typedObject.getUsername())
                    && this.getEmail().equals(typedObject.getEmail());
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        int hashCodeValue =
                +(71121 * 79)
                        + this.getId().hashCode()
                        + this.getUsername().hashCode()
                        + this.getEmail().hashCode();

        return hashCodeValue;
    }

    @Override
    public String toString() {
        return "ProjectOwner{" + super.toString() + "\n" +
                "projects=" + projects +
                '}';
    }
}
