package com.bescobarres.football.service;

import com.bescobarres.football.application.service.PlayerService;
import com.bescobarres.football.application.service.TrainingService;
import com.bescobarres.football.application.service.impl.TrainingPlayerServiceImpl;
import com.bescobarres.football.domain.dto.PlayerDto;
import com.bescobarres.football.domain.dto.TrainingInputDto;
import com.bescobarres.football.domain.dto.TrainingOutputDto;
import com.bescobarres.football.domain.entity.TrainingEntity;
import com.bescobarres.football.infrastructure.mapper.TrainingMapper;
import com.bescobarres.football.service.builder.TrainingBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrainingPlayerServiceTest {


    @Mock
    private TrainingService trainingService;
    @Mock
    private PlayerService playerService;

    @Mock
    private TrainingMapper trainingMapper;

    @InjectMocks
    private TrainingPlayerServiceImpl trainingPlayerService;


    @Test
    public void StartingLineUpService_GetStartingLineUpPlayers_ReturnListOfStartingLineUpTeam() {
        List<TrainingInputDto> trainingsInputDto = TrainingBuilder.getBuildTrainingsInput();
        List<TrainingOutputDto> trainingsOutputDto = TrainingBuilder.getBuildThreeTrainingsByFivePlayer();
        PlayerDto newPlayer = trainingsOutputDto.get(0).getPlayer();
        newPlayer.setId(1L);


        when(trainingMapper.dtoToModel(trainingsInputDto)).thenReturn(trainingsOutputDto);
        when(playerService.create(any())).thenReturn(newPlayer);
        doNothing().when(trainingService).saveTrainings(trainingsOutputDto);

        List<TrainingOutputDto> startingLineUpPlayers =
                trainingPlayerService.proccess(trainingsInputDto);

        Assertions.assertNotNull(startingLineUpPlayers);


    }
}
