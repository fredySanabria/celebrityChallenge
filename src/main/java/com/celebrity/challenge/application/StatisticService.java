package com.celebrity.challenge.application;

import com.celebrity.challenge.domain.Person;
import com.celebrity.challenge.domain.Vote;
import com.celebrity.challenge.exception.CelebrityNotFoundException;
import com.celebrity.challenge.repository.PersonRepository;
import com.celebrity.challenge.repository.VoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Domain service have the popularity statistics business logic
 */
@Service
@Slf4j
public class StatisticService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private PersonRepository personRepository;

    static Map<Integer,Long> votesForPersonMap;

    static Comparator<Person> voteComparator = (personA, personB) -> getNumberOfVotes(personA,votesForPersonMap)
            .compareTo(getNumberOfVotes(personB,votesForPersonMap)) ;

    /**
     * This method is in charge of get all votes of the team
     * @return List<Votes>
     */
    public List<Vote> getVotes(){
        return voteRepository.findAll();
    }

    /**
     * This method group the votes by person and compare the votes to find the celebrity
     * @return
     */
    public Person getMostVotedPerson(){
        votesForPersonMap = getVotes()
                .stream()
                .collect(Collectors.groupingBy(id -> id.getId_person(), Collectors.counting()));
        log.info("Map of votes: {}",votesForPersonMap);
        if(votesForPersonMap.size() == 0){
                throw new CelebrityNotFoundException("No one has recognized the celebrity");
        }
        Optional<Person> mostPopularPerson =  personRepository.findAll().stream().max(voteComparator);
        if(mostPopularPerson.isPresent()){
            return mostPopularPerson.get();
        }
        return null;
    }

    /**
     * This method verifies the number of votes, if the person don´t have votes return 0 votes
     * @param person
     * @param votesMap
     * @return
     */
    private static Long getNumberOfVotes(Person person, Map<Integer,Long> votesMap){
        return (votesMap.containsKey(person.getId())) ? votesMap.get(person.getId()) : 0L;
    }
}
