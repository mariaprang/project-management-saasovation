package com.projectmanagement.saasovation.board.infrustructure;

import com.projectmanagement.saasovation.board.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepository {

    @Autowired
    private BoardRepository boardRepository;

    public void createBoard(Board board){
        boardRepository.createBoard(board);
    }

    public void deleteBoard(Board boardToDelete){
        boardRepository.deleteBoard(boardToDelete);
    }

    public List<Board> listAllBoards(){
        return boardRepository.listAllBoards();
    }
}
