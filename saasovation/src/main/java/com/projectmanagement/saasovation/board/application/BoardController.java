package com.projectmanagement.saasovation.board.application;

import com.projectmanagement.saasovation.board.domain.Board;
import com.projectmanagement.saasovation.board.infrustructure.BoardRepository;
import com.projectmanagement.saasovation.project.domain.Project;
import com.projectmanagement.saasovation.project.infrastructure.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @PostMapping("/newBoardForProject/{id}")
    public String createTeam(@PathVariable("id") long id,
                             @RequestParam("boardName") String fullName, Model model) throws Exception {
        Project currentProject = projectRepository.findProjectById(id);
        Board board = new Board(fullName);
        board.setProject(currentProject);
        currentProject.addBoard(board);
        boardRepository.createBoard(board);
        return "redirect:/projects/" + id;
    }

}
