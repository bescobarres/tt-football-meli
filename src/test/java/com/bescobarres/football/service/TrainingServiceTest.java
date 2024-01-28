package com.bescobarres.football.service;

import com.bescobarres.football.application.service.impl.TrainingServiceImpl;
import com.bescobarres.football.domain.dto.PlayerDto;
import com.bescobarres.football.domain.dto.TrainingOutputDto;
import com.bescobarres.football.domain.entity.PlayerEntity;
import com.bescobarres.football.domain.entity.TrainingEntity;
import com.bescobarres.football.domain.model.Stats;
import com.bescobarres.football.infrastructure.mapper.TrainingMapper;
import com.bescobarres.football.infrastructure.repository.TrainingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainingServiceTest {

    @Mock
    private TrainingRepository trainingRepository;
    @Mock
    private TrainingMapper trainingMapper;

    @InjectMocks
    private TrainingServiceImpl trainingService;

    PlayerEntity playerEntity;
    TrainingEntity trainingEntity;

    @BeforeEach
    public void init() {
        playerEntity = PlayerEntity.builder().name("Test").build();
        trainingEntity = TrainingEntity.builder().id(1L).playerId(1L)
                .distance(100D)
                .passes(20L)
                .power(100D)
                .time(50L)
                .date(LocalDate.now()).build();
    }

    @Test
    public void TrainingService_CreateTraining_ReturnTrainingSaved() {
        TrainingOutputDto trainingOutputDto = TrainingOutputDto.builder()
                .player(PlayerDto.builder().id(1L).name("Player 1").build())
                .stats(Stats.builder()
                        .distance(100D)
                        .passes(20L)
                        .power(100D)
                        .time(50L).build())
                .date(LocalDate.now())
                .build();

        when(trainingRepository.save(trainingEntity)).thenReturn(trainingEntity);
        when(trainingMapper.modelToEntity(trainingOutputDto)).thenReturn(trainingEntity);
        when(trainingMapper.entityToModel(trainingEntity)).thenReturn(trainingOutputDto);

        TrainingOutputDto trainingOutputDtoSaved = trainingService.create(trainingOutputDto);

        Assertions.assertNotNull(trainingOutputDtoSaved);
        Assertions.assertEquals(trainingOutputDtoSaved.getId(), trainingOutputDto.getId());

    }


    @Test
    public void TrainingService_CreateTrainings_VerifyTrainingSaveMethod() {
        TrainingOutputDto trainingOutputDto = TrainingOutputDto.builder()
                .player(PlayerDto.builder().name("Player 1").build())
                .stats(Stats.builder()
                        .distance(100D)
                        .passes(20L)
                        .power(100D)
                        .time(50L).build())
                .build();

        List<TrainingOutputDto> trainingsOutputDto = List.of(trainingOutputDto);

        when(trainingRepository.save(trainingEntity)).thenReturn(trainingEntity);
        when(trainingMapper.modelToEntity(trainingOutputDto)).thenReturn(trainingEntity);
        when(trainingMapper.entityToModel(trainingEntity)).thenReturn(trainingOutputDto);

        trainingService.saveTrainings(trainingsOutputDto);

        verify(trainingRepository, times(1)).save(trainingEntity);
    }


    @Test
    public void TrainingService_GettingTraining_VerifyGettingTrainingMethod() {
        TrainingOutputDto trainingOutputDto = TrainingOutputDto.builder()
                .player(PlayerDto.builder().name("Player 1").build())
                .stats(Stats.builder()
                        .distance(100D)
                        .passes(20L)
                        .power(100D)
                        .time(50L).build())
                .build();

        LocalDate date = LocalDate.now();
        LocalDate initialDayOfWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate lastDayOfWeek = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        List<TrainingOutputDto> trainingsOutputDto = List.of(trainingOutputDto);

        when(trainingRepository.findByDateBetween(initialDayOfWeek, lastDayOfWeek)).thenReturn(List.of(trainingEntity));
        when(trainingMapper.entityToModel(List.of(trainingEntity))).thenReturn((List.of(trainingOutputDto)));

        trainingService.getTrainingsByWeek(date);

        verify(trainingRepository, times(1)).findByDateBetween(initialDayOfWeek, lastDayOfWeek);
    }


}
