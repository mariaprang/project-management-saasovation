package com.projectmanagement.saasovation.team.infrustructure.repositories.team;

import com.projectmanagement.saasovation.team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITeamRepository extends JpaRepository<Team, Long> {
}
