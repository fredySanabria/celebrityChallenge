package com.celebrity.challenge.rest_interface;

import com.celebrity.challenge.DTO.PersonDTO;
import com.celebrity.challenge.application.StatisticService;
import com.celebrity.challenge.application.TeamService;
import com.celebrity.challenge.application.VoteService;
import com.celebrity.challenge.domain.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ApplicationController.class)
public class ApplicationControllerTest {
    @MockBean
    TeamService teamService;

    @MockBean
    StatisticService statisticService;

    @MockBean
    VoteService voteService;

    @Autowired
    private MockMvc mockMvc;

    private final PersonDTO testPerson = PersonDTO.builder()
            .id(5)
            .name("test name")
            .job("testJob")
            .image("image.test")
            .build();
    private List<PersonDTO> teamList;
    @Before
    public void init(){
        teamList = new ArrayList<>();
        teamList.add(testPerson);
    }

    @Test
    public void whenBrowserSendAllTeamPetitionThenReturnExpectedResult() throws Exception {
        when(teamService.getTeamList()).thenReturn(teamList);
        mockMvc.perform( MockMvcRequestBuilders
                .get("/team")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("test name")));
    }

    @Test
    public void whenBrowserSendMostImportantPersonByNamePetitionThenReturnExpectedResult() throws Exception {
        when(statisticService.getCelebrityByName()).thenReturn(testPerson);
        mockMvc.perform( MockMvcRequestBuilders
                .get("/celebrityInTeamByName")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("test name")));
    }
}
