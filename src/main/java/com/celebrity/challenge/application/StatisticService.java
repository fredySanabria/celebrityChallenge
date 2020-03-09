package com.celebrity.challenge.application;

import com.celebrity.challenge.DTO.PersonDTO;
import com.celebrity.challenge.DTO.PersonMapper;
import com.celebrity.challenge.exception.CelebrityNotFoundException;
import com.celebrity.challenge.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

/**
 * Domain service have the popularity statistics business logic
 */
@Service
public class StatisticService {

    @Autowired
    private VoteService voteService;

    @Autowired
    private PersonRepository personRepository;

    static final Comparator<PersonDTO> voteComparator = (personA, personB) -> personA.getVotes().compareTo(personB.getVotes()) ;


    /**
     * This method group the votes by person and compare the votes to find the celebrity
     * @return
     */
    public PersonDTO getMostVotedPerson() throws CelebrityNotFoundException {
        Map<Integer,Long> voteMap = voteService.getVotes();
        Optional<PersonDTO> mostPopularPerson =  personRepository.findAll().stream()
                .map(person -> PersonMapper.mapToDTO(person, voteMap))
                .max(voteComparator);
        return mostPopularPerson.orElse(null);
    }
}
