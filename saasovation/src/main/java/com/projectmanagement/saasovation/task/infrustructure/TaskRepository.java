package com.projectmanagement.saasovation.task.infrustructure;

import com.projectmanagement.saasovation.task.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository {

    @Autowired
    private ITaskRepository taskRepository;

    public void createTask(Task task){
        taskRepository.save(task);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
}
