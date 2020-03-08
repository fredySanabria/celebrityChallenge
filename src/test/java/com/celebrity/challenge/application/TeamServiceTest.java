package com.celebrity.challenge.application;

import com.celebrity.challenge.domain.Person;
import com.celebrity.challenge.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TeamServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private TeamService teamService;

    private Person testPerson = Person.builder()
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
    public void whenControllerSendAllTeamRequestThenReturnExpectedResult(){
        when(personRepository.findAll()).thenReturn(personList);
        List<Person> resultList = teamService.getTeamList();
        assertThat(resultList).isNotNull();
    }
}
