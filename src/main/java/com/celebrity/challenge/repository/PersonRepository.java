package com.celebrity.challenge.repository;


import com.celebrity.challenge.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
