package com.celebrity.challenge.application;

import com.celebrity.challenge.DTO.PersonDTO;
import com.celebrity.challenge.DTO.PersonMapper;
import com.celebrity.challenge.domain.Person;
import com.celebrity.challenge.exception.CelebrityNotFoundException;
import com.celebrity.challenge.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.celebrity.challenge.DTO.PersonMapper.mapToDTO;

/**
 * This domain service have the team person business logic
 */
@Service
public class TeamService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private VoteService voteService;

    /**
     * Retrieve the entire team list
     * @return
     */
    public List<PersonDTO> getTeamList() throws CelebrityNotFoundException {
        Map<Integer,Long> voteMap = voteService.getVotes();
        return personRepository.findAll().stream()
                .map(person -> mapToDTO(person, voteMap))
                .collect(Collectors.toList());
    }

    /**
     * This method creates a person
     * @param newPerson
     */
    public Person addPerson(Person newPerson) {
        return personRepository.save(newPerson);
    }
}
