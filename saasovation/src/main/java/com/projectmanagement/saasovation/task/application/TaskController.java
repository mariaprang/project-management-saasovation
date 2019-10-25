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


    @RequestMapping("/createTaskForBoard/{id}")
    private String addNewTask(@PathVariable("id") long id,
                              @RequestParam("fullName") String fullName,
                              @RequestParam("taskHeader") String taskHeader) {

        Board board = boardRepository.getBoardById(id);
       log.info("RIGHT HERE: "+board.toString());
        String[] memberCredentials = fullName.split(" ");

        Member member = memberRepository.loadMemberByFullName(memberCredentials[0], memberCredentials[1]);

        Task task = new Task(taskHeader);

        assignTaskOnBoardToMember(member, board, task);
        taskRepository.createTask(task);
        return "redirect:/projects/" + id;
    }

    private void assignTaskOnBoardToMember(Member member, Board board, Task task){
        member.addTask(task);
        board.addTaskToBoard(task);
        task.setTaskBoard(board);
        task.setProject(board.getProject());
        task.setTaskAssignedTo(member);
    }
}
