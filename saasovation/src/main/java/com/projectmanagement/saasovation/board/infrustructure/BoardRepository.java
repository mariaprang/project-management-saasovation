package com.projectmanagement.saasovation.board.infrustructure;

import com.projectmanagement.saasovation.board.domain.Board;
import com.projectmanagement.saasovation.task.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class BoardRepository {

    @Autowired
    private IBoardRepository boardRepository;

    public void createBoard(Board board) {
        boardRepository.save(board);
    }

    public void saveBoard(Board board){
        boardRepository.save(board);
    }

    public void deleteBoard(Board boardToDelete) {
        boardRepository.delete(boardToDelete);
    }

    public List <Board> listAllBoards() {
        return boardRepository.findAll();
    }

    public Board getBoardById(Long id){
        return boardRepository.findById(id).get();
    }

//    public Set<Task> getTasksForBoard(Long id){
//        Board board = getBoardById(id);
//        return board.getTasks();
//    }
}
