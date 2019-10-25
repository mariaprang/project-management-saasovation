package com.projectmanagement.saasovation.task.infrustructure;

import com.projectmanagement.saasovation.task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskRepository extends JpaRepository<Task, Long> {


}
