package com.projectmanagement.saasovation.task.domain;


import com.projectmanagement.saasovation.board.domain.Board;
import com.projectmanagement.saasovation.project.domain.Project;
import com.projectmanagement.saasovation.team.domain.BaseEntity;
import com.projectmanagement.saasovation.team.domain.Member;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity <Long> {

    @OneToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @OneToOne
    @JoinColumn(name = "assignedTo_id", nullable = false)
    private Member taskAssignedTo;

    @Column(name = "task_header")
    private String taskHeader;

    @OneToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board taskBoard;


    public Task() {
    }

    public Task(String taskTitle) {
        this.taskHeader = taskTitle;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Member getTaskAssignedTo() {
        return taskAssignedTo;
    }

    public void setTaskAssignedTo(Member taskAssignedTo) {
        this.taskAssignedTo = taskAssignedTo;
    }

    public String getTaskHeader() {
        return taskHeader;
    }

    public void setTaskHeader(String taskHeader) {
        this.taskHeader = taskHeader;
    }

    public Board getTaskBoard() {
        return taskBoard;
    }

    public void setTaskBoard(Board taskBoard) {
        this.taskBoard = taskBoard;
    }

    @Override
    public String toString() {
        return "Task{" +
                "project=" + project +
                ", taskAssignedTo=" + taskAssignedTo +
                ", taskHeader='" + taskHeader + '\'' +
                ", taskBoard=" + taskBoard +
                '}';
    }

    /******************** AGGREGATE TYPICAL METHODS ***********************/


    /******************** AGGREGATE TYPICAL METHODS ***********************/
}
