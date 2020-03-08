package com.celebrity.challenge.application;

import com.celebrity.challenge.domain.Vote;
import com.celebrity.challenge.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
}
