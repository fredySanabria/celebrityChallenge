package com.celebrity.challenge.application;

import com.celebrity.challenge.DTO.PersonDTO;
import com.celebrity.challenge.domain.Person;
import com.celebrity.challenge.exception.CelebrityNotFoundException;
import com.celebrity.challenge.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TeamServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private VoteService voteService;

    @InjectMocks
    private TeamService teamService;

    private final Person testPerson = Person.builder()
            .id(5)
            .name("test name")
            .job("testJob")
            .image("image.test")
            .build();
    private List<Person> personList;
    @Before
    public void init(){
        personList = new ArrayList<>();
        personList.add(testPerson);
    }

    @Test
    public void whenControllerSendAllTeamRequestThenReturnExpectedResult() throws CelebrityNotFoundException {
        when(voteService.getVotes()).thenReturn(new HashMap<>());
        when(personRepository.findAll()).thenReturn(personList);
        List<PersonDTO> resultList = teamService.getTeamList();
        assertThat(resultList).isNotNull();
    }

    @Test
    public void whenControllerSendCreatePersonRequestThenReturnExpectedResult(){
        when(personRepository.save(testPerson)).thenReturn(testPerson);
        Person result = teamService.addPerson(testPerson);
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("test name");
    }
}
