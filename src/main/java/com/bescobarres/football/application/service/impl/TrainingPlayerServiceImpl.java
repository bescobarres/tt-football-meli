package com.bescobarres.football.application.service.impl;

import com.bescobarres.football.application.service.PlayerService;
import com.bescobarres.football.application.service.TrainingPlayerService;
import com.bescobarres.football.application.service.TrainingService;
import com.bescobarres.football.domain.dto.TrainingOutputDto;
import com.bescobarres.football.domain.dto.TrainingInputDto;
import com.bescobarres.football.infrastructure.mapper.TrainingMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingPlayerServiceImpl implements TrainingPlayerService {

    private final PlayerService playerService;
    private final TrainingService trainingService;
    private final TrainingMapper trainingMapper;

    public TrainingPlayerServiceImpl(PlayerService playerService, TrainingService trainingService, TrainingMapper trainingMapper) {
        this.playerService = playerService;
        this.trainingService = trainingService;
        this.trainingMapper = trainingMapper;
    }

    @Override
    public List<TrainingOutputDto> proccess(List<TrainingInputDto> trainingsDto) {
        List<TrainingOutputDto> trainingsOutputDto =  trainingMapper.dtoToModel(trainingsDto);
        savePlayers(trainingsOutputDto);
        trainingService.saveTrainings(trainingsOutputDto);
        return trainingsOutputDto;
    }

    private void savePlayers(List<TrainingOutputDto> trainingsOutputDto) {
        trainingsOutputDto
                .forEach(training -> {
                training.getPlayer().setId(
                        playerService.create(training.getPlayer()).getId());
        });

    }

}
