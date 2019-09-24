package com.projectmanagement.saasovation.team.domain;

import com.projectmanagement.saasovation.task.domain.Task;

import javax.persistence.Entity;

@Entity
public class Member {

    private String fristName;
    private String lastName;
    private String email;
    private String username;

   // private Set<Task> memberAssignedTasks;



    public void addTaskToMember(){

    }

}
