package com.celebrity.challenge.application;

import com.celebrity.challenge.domain.Vote;
import com.celebrity.challenge.exception.CelebrityNotFoundException;
import com.celebrity.challenge.repository.VoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VoteService {

    @Autowired
    VoteRepository voteRepository;

    /**
     * This method persist a vote
     * @param vote
     */
    public void addVote(Vote vote){
        voteRepository.save(vote);
    }

    /**
     * Return a Map with the votes count grouping by id
     * @return
     */
    public Map<Integer,Long> getVotes() throws CelebrityNotFoundException{
        Map<Integer,Long> votesForPersonMap = voteRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(id -> id.getId_person(), Collectors.counting()));
        log.info("Map of votes: {}",votesForPersonMap);
        if(votesForPersonMap.size() == 0){
            throw new CelebrityNotFoundException("No one has recognized the celebrity");
        }
        return votesForPersonMap;
    }
}
