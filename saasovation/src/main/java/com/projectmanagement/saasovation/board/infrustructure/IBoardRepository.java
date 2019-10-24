package com.projectmanagement.saasovation.board.infrustructure;

import com.projectmanagement.saasovation.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBoardRepository extends JpaRepository<Board, Long> {
}
