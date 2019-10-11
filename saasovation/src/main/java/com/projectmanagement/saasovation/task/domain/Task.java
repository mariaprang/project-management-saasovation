package com.projectmanagement.saasovation.task.domain;


import com.projectmanagement.saasovation.board.Board;
import com.projectmanagement.saasovation.team.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Task extends BaseEntity<Long> {

    private Long projectID;
    private Long taskCreatedByID;
    private Long taskAssignedTo;
    private String taskDescription;

    @ManyToOne
    @JoinColumn(name="board_id", nullable=false)
    private Board taskBoard;


}
