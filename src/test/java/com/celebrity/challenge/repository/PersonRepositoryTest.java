package com.celebrity.challenge.repository;

import com.celebrity.challenge.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {
    @Autowired
    PersonRepository personRepository ;

    private final Person testPerson = Person.builder()
            .id(5)
            .name("test name")
            .job("testJob")
            .image("image.test")
            .build();

    @Test
    public void givenTeam_whenFindAll_thenExpectedReturned() {
        personRepository.save(testPerson);
        assertThat(personRepository.findAll()).isNotNull();
    }
}
