package com.projectmanagement.saasovation.board;

import com.projectmanagement.saasovation.project.domain.Project;
import com.projectmanagement.saasovation.team.domain.BaseEntity;
import com.projectmanagement.saasovation.team.domain.Member;
import com.projectmanagement.saasovation.team.domain.Team;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "board")
public class Board extends BaseEntity<Long>{


    @Transient
    @OneToMany(mappedBy = "taskBoard")
    private Set<Team> tasks;

    @Column(name="board_name")
    private String boardName;

    @ManyToOne
    @JoinColumn(name="project_id", nullable=false)
    private Project project;




}
