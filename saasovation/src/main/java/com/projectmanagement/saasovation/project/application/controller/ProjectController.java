package com.projectmanagement.saasovation.project.application.controller;

import com.projectmanagement.saasovation.board.domain.Board;
import com.projectmanagement.saasovation.board.infrustructure.BoardRepository;
import com.projectmanagement.saasovation.project.domain.Project;
import com.projectmanagement.saasovation.project.infrastructure.ProjectRepository;
import com.projectmanagement.saasovation.task.domain.Task;
import com.projectmanagement.saasovation.task.infrustructure.TaskRepository;
import com.projectmanagement.saasovation.team.domain.Member;
import com.projectmanagement.saasovation.team.domain.Team;
import com.projectmanagement.saasovation.team.infrustructure.repositories.member.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ProjectController {

    private static final Logger log = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private TaskRepository taskRepository;


    @GetMapping(value = {"/projects/{id}"})
    public String getProjectView(@PathVariable("id") long id, Model model) {
        Project project = projectRepository.findProjectById(id);
        model.addAttribute("project", project);
        Set <Member> projectMembers = new HashSet <>();
        model.addAttribute("teams", project.getTeams());

        for (Team team : project.getTeams()) {
            for (Member member : team.getTeamMembers()) {
                projectMembers.add(member);
            }
        }
        model.addAttribute("projectMembers", projectMembers);

        List <Member> allMembers = memberRepository.findAllMembers();

        allMembers.removeAll(projectMembers);


        model.addAttribute("allMembers", allMembers);
        model.addAttribute("boards", getBoardsForProject(id));
        model.addAttribute("tasks", taskRepository.getAllTasks());
        return "project";
    }

    private List <Board> getBoardsForProject(Long projectID) {
        List<Board> boards = new ArrayList <>();
       for(Board board : boardRepository.listAllBoards()){
           if(board.getProject().getId().equals(projectID)){
               board.setTasks(getTasksForBoard(board.getId()));
               boards.add(board);
           }
       }
        return boards;
    }

    private List<Task> getTasksForBoard(Long id){
        List<Task> tasks = new ArrayList<>();
        for(Task task : taskRepository.getAllTasks()){
            if(task.getTaskBoard().getId().equals(id)){
                tasks.add(task);
            }
        }
        return tasks;
    }


//    private List<Task> converToList(Set<Task> tasks){
//        List list = new ArrayList();
//        list.addAll(tasks);
//        return list;
//    }

}
