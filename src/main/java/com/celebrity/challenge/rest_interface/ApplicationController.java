package com.celebrity.challenge.rest_interface;

import com.celebrity.challenge.DTO.PersonDTO;
import com.celebrity.challenge.application.StatisticService;
import com.celebrity.challenge.application.TeamService;
import com.celebrity.challenge.application.VoteService;
import com.celebrity.challenge.domain.Person;
import com.celebrity.challenge.domain.Vote;
import com.celebrity.challenge.exception.CelebrityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class ApplicationController {

    @Autowired
    TeamService teamService;

    @Autowired
    StatisticService statisticService;

    @Autowired
    VoteService voteService;

    @GetMapping(path = "/team")
    public List<PersonDTO> getTeam() throws CelebrityNotFoundException {
        return teamService.getTeamList();
    }

    @GetMapping(path = "/celebrityInTeam")
    public PersonDTO getCelebrity() throws CelebrityNotFoundException {
        return statisticService.getMostVotedPerson();
    }

    @GetMapping(path = "/celebrityInTeamByName")
    public PersonDTO getCelebrityByName() throws CelebrityNotFoundException {
        return statisticService.getCelebrityByName();
    }

    @PostMapping("/votes")
    public void createVote(@RequestBody Vote newVote) {
        voteService.addVote(newVote);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/team")
                .build()
                .toUri();
        ResponseEntity.created(location).build();
    }

    @PostMapping("/team/person")
    public void createPerson(@RequestBody Person newPerson) {
        teamService.addPerson(newPerson);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/team")
                .build()
                .toUri();
        ResponseEntity.created(location).build();
    }
}
