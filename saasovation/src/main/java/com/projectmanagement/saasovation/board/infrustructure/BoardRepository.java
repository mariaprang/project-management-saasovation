package com.projectmanagement.saasovation.board.infrustructure;

import com.projectmanagement.saasovation.board.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepository {

    @Autowired
    private IBoardRepository boardRepository;

    public void createBoard(Board board){
        boardRepository.save(board);
    }

    public void deleteBoard(Board boardToDelete){
        boardRepository.delete(boardToDelete);
    }

    public List<Board> listAllBoards(){
        return boardRepository.findAll();
    }
}
