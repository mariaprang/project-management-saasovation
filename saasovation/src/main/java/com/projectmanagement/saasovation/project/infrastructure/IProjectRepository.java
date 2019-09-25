package com.projectmanagement.saasovation.project.infrastructure;

import com.projectmanagement.saasovation.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProjectRepository extends JpaRepository<Project, Long> {


}
