package com.projectmanagement.saasovation.team.infrustructure.repositories.team;

import com.projectmanagement.saasovation.team.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeamRepository {

    @Autowired
    private ITeamRepository teamRepository;

    public void saveTeam(Team team){
         teamRepository.save(team);
    }

    public List <Team> getAllTeams(){
        return teamRepository.findAll();
    }


}
