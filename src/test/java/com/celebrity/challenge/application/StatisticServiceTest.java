package com.celebrity.challenge.application;

import com.celebrity.challenge.DTO.PersonDTO;
import com.celebrity.challenge.domain.Person;
import com.celebrity.challenge.domain.Vote;
import com.celebrity.challenge.exception.CelebrityNotFoundException;
import com.celebrity.challenge.repository.PersonRepository;
import com.celebrity.challenge.repository.VoteRepository;
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
public class StatisticServiceTest {

    @Mock
    private VoteService voteService;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    StatisticService statisticService;

    private final Vote testVote = Vote.builder().id(1).id_person(100).build();

    private List<Vote> totalVotes;

    private final Person testPerson = Person.builder()
            .id(5)
            .name("test name")
            .job("testJob")
            .image("image.test")
            .build();
    private List<Person> teamList;

    @Before
    public void init(){
        totalVotes = new ArrayList<>();
        totalVotes.add(testVote);
        teamList = new ArrayList<>();
        teamList.add(testPerson);
    }

    @Test
    public void whenControllerSendMostImportantPersonRequestThenReturnExpectedResult() throws CelebrityNotFoundException {
        when(voteService.getVotes()).thenReturn(new HashMap<>());
        when(personRepository.findAll()).thenReturn(teamList);
        PersonDTO result = statisticService.getMostVotedPerson();
        assertThat(result).isNotNull();
        String celebrityName = result.getName();
        assertThat(celebrityName).isEqualTo("test name");
    }

    @Test(expected = CelebrityNotFoundException.class)
    public void whenControllerSendMostImportantPersonRequestThenReturnException() throws CelebrityNotFoundException{
        Person testPerson2 = Person.builder()
                .id(6)
                .name("other name")
                .job("otherJob")
                .image("otherImage.test")
                .build();
        teamList.add(testPerson2);
        when(voteService.getVotes()).thenThrow(CelebrityNotFoundException.class);
        PersonDTO result = statisticService.getMostVotedPerson();
    }
}
