package com.bescobarres.football.controller;

import com.bescobarres.football.application.service.StartingLineUpTeamService;
import com.bescobarres.football.application.service.TrainingPlayerService;
import com.bescobarres.football.domain.dto.TrainingInputDto;
import com.bescobarres.football.infrastructure.controller.StartingLineUpTeamController;
import com.bescobarres.football.infrastructure.controller.TrainingPlayerController;
import com.bescobarres.football.service.builder.TrainingBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = StartingLineUpTeamController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)public class StartingLineUpTeamControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StartingLineUpTeamService startingLineUpTeamService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void StartingLineUpTeamController_GetStartingLineUpTeam_ReturnThereIsNotEnoughInformation() throws Exception {

        given(startingLineUpTeamService.getStartingLineUpPlayers(LocalDate.now(), 1))
                .willAnswer(InvocationOnMock::getArguments);

        ResultActions response = mockMvc.perform(get("/team")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }

}
