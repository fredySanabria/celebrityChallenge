package com.celebrity.challenge.application;

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
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatisticServiceTest {

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    StatisticService statisticService;

    private Vote testVote = Vote.builder().id(1).id_person(100).build();

    private List<Vote> totalVotes;

    private Person testPerson = Person.builder()
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
    public void whenControllerSendAllVotesRequestThenReturnExpectedResult(){
        when(voteRepository.findAll()).thenReturn(totalVotes);
        List<Vote> result = statisticService.getVotes();
        assertThat(result).isNotNull();
        long totalVotes = result.stream().filter(vote -> vote.getId_person() == 100).count();
        assertThat(totalVotes).isEqualTo(1L);
    }

    @Test
    public void whenControllerSendMostImportantPersonRequestThenReturnExpectedResult(){
        when(voteRepository.findAll()).thenReturn(totalVotes);
        when(personRepository.findAll()).thenReturn(teamList);
        Person result = statisticService.getMostVotedPerson();
        assertThat(result).isNotNull();
        String celebrityName = result.getName();
        assertThat(celebrityName).isEqualTo("test name");
    }

    @Test(expected = CelebrityNotFoundException.class)
    public void whenControllerSendMostImportantPersonRequestThenReturnException(){
        Person testPerson2 = Person.builder()
                .id(6)
                .name("other name")
                .job("otherJob")
                .image("otherImage.test")
                .build();
        teamList.add(testPerson2);
        when(voteRepository.findAll()).thenReturn(new ArrayList<>());
        when(personRepository.findAll()).thenReturn(teamList);
        Person result = statisticService.getMostVotedPerson();
    }
}
