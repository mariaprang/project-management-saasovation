package com.projectmanagement.saasovation.task.application;

import com.projectmanagement.saasovation.board.domain.Board;
import com.projectmanagement.saasovation.board.infrustructure.BoardRepository;
import com.projectmanagement.saasovation.task.domain.Task;
import com.projectmanagement.saasovation.task.infrustructure.TaskRepository;
import com.projectmanagement.saasovation.team.application.MemberController;
import com.projectmanagement.saasovation.team.domain.Member;
import com.projectmanagement.saasovation.team.infrustructure.repositories.member.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);


    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;


    @RequestMapping("/createTaskForBoard/{boardID}/onProject{projID}")
    private String addNewTask(@PathVariable("boardID") long boardID,
                              @PathVariable("projID") long projID,
                              @RequestParam("fullName") String fullName,
                              @RequestParam("taskHeader") String taskHeader) {

        printLogger("BOARD ID: "+boardID);
        printLogger("PROJECT ID: "+projID);
        Board board = boardRepository.getBoardById(boardID);
        printLogger(board.toString());

        String[] memberCredentials = fullName.split(" ");

        Member member = memberRepository.loadMemberByFullName(memberCredentials[0], memberCredentials[1]);

        Task task = new Task(taskHeader);

        assignTaskOnBoardToMember(member, board, task);
        taskRepository.createTask(task);
        boardRepository.createBoard(board);

        return "redirect:/projects/" + projID;
    }

//    @RequestMapping("/showAllTasks")
//    private String showAllTasks(Model model){
//    }

    private void printLogger(String message){
        log.info("==============================================================");
        log.info("********************"+message+"***************************");
        log.info("==============================================================");
    }

    private void assignTaskOnBoardToMember(Member member, Board board, Task task){
        task.setTaskBoard(board);
        task.setProject(board.getProject());
        task.setTaskAssignedTo(member);
        member.addTask(task);
        board.addTaskToBoard(task);
    }
}
