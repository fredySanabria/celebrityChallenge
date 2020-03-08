package com.celebrity.challenge.repository;

import com.celebrity.challenge.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote,Integer> {

}
