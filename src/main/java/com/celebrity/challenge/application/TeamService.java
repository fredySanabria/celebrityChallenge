package com.celebrity.challenge.application;

import com.celebrity.challenge.domain.Person;
import com.celebrity.challenge.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This domain service have the team person business logic
 */
@Service
public class TeamService {
    @Autowired
    private PersonRepository personRepository;

    /**
     * Retrieve the entire team list
     * @return
     */
    public List<Person> getTeamList(){
        return personRepository.findAll();
    }
}
