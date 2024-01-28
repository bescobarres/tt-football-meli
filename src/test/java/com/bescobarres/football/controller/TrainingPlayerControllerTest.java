package com.bescobarres.football.controller;

import com.bescobarres.football.application.service.TrainingPlayerService;
import com.bescobarres.football.domain.dto.TrainingInputDto;
import com.bescobarres.football.infrastructure.controller.TrainingPlayerController;
import com.bescobarres.football.service.builder.TrainingBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
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

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = TrainingPlayerController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class TrainingPlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainingPlayerService trainingPlayerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void TrainingPlayerController_CreateTrainingAndPlayer_ReturnListOfTrainingsByPlayer() throws Exception {

        List<TrainingInputDto> trainingsInputDto = TrainingBuilder.getBuildTrainingsInput();

        given(trainingPlayerService.proccess(ArgumentMatchers.any())).willAnswer(invocation ->
                invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/training")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(trainingsInputDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].playerId", CoreMatchers.is(1)))
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void TrainingPlayerController_CreateTrainingAndPlayer_ReturnExceptionForValidationPasses() throws Exception {

        List<TrainingInputDto> trainingsInputDto = List.of(TrainingInputDto.builder()
                .power(100D)
                .time(10L)
                .date(LocalDate.now())
                .distance(30D)
                .name("Player 1")
                .build());

        given(trainingPlayerService.proccess(ArgumentMatchers.any())).willAnswer(invocation ->
                invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/training")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(trainingsInputDto)));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", CoreMatchers.is("El campo passes no debe ser NULL")))
                .andDo(MockMvcResultHandlers.print());

    }

}
