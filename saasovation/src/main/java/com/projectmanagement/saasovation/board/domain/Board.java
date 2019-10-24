package com.projectmanagement.saasovation.board.domain;

import com.projectmanagement.saasovation.project.domain.Project;
import com.projectmanagement.saasovation.team.domain.BaseEntity;
import com.projectmanagement.saasovation.team.domain.Team;

import javax.persistence.*;
import java.util.Objects;
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


    @Override
    public String toString() {
        return "Board{" +
                "tasks=" + tasks +
                ", boardName='" + boardName + '\'' +
                ", project=" + project +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board)) return false;
        if (!super.equals(o)) return false;
        Board board = (Board) o;
        return Objects.equals(boardName, board.boardName) &&
                Objects.equals(project, board.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), boardName, project);
    }
}
