package com.bescobarres.football.service;

import com.bescobarres.football.application.service.PlayerService;
import com.bescobarres.football.application.service.TrainingService;
import com.bescobarres.football.application.service.impl.StartingLineUpTeamServiceImpl;
import com.bescobarres.football.domain.dto.TrainingOutputDto;
import com.bescobarres.football.domain.entity.PlayerEntity;
import com.bescobarres.football.domain.entity.TrainingEntity;
import com.bescobarres.football.domain.exception.ApiRequestExceptionNotFound;
import com.bescobarres.football.domain.model.StartingLineUp;
import com.bescobarres.football.service.builder.TrainingBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StartingLineUpTeamServiceTest {

    @Mock
    private TrainingService trainingService;
    @Mock
    private PlayerService playerService;

    @InjectMocks
    private StartingLineUpTeamServiceImpl startingLineUpTeamService;

    @Test
    public void StartingLineUpService_GetStartingLineUpPlayers_ReturnListOfStartingLineUpTeam() {
        List<TrainingOutputDto> trainingsOutputDto = TrainingBuilder.getBuildThreeTrainingsByFivePlayer();

        LocalDate day = LocalDate.now();
        int startingLineUpQuantity = 5;

        when(trainingService.getTrainingsByWeek(day)).thenReturn(trainingsOutputDto);

        List<StartingLineUp> startingLineUpPlayers = startingLineUpTeamService.getStartingLineUpPlayers(day, startingLineUpQuantity);

        Assertions.assertNotNull(startingLineUpPlayers);
//        Assertions.assertEquals(trainingOutputDtoSaved.getId(), trainingOutputDto.getId());

    }

    @Test
    public void StartingLineUpService_GetStartingLineUpPlayersWithoutEnoughInformation_ReturnException() {
        List<TrainingOutputDto> trainingsOutputDto = TrainingBuilder.getBuildTwoTrainingsByFivePlayer();
        String expectedMessage = "No hay suficiente información";
        LocalDate day = LocalDate.now();
        int startingLineUpQuantity = 5;

        when(trainingService.getTrainingsByWeek(day)).thenReturn(trainingsOutputDto);


        Exception exception = Assertions.assertThrows(ApiRequestExceptionNotFound.class, () ->
                startingLineUpTeamService.getStartingLineUpPlayers(day, startingLineUpQuantity)
        );

        Assertions.assertEquals(expectedMessage, exception.getMessage());

    }
}
