package com.bescobarres.football.application.service.impl;

import com.bescobarres.football.application.service.PlayerService;
import com.bescobarres.football.application.service.TrainingPlayerService;
import com.bescobarres.football.application.service.TrainingService;
import com.bescobarres.football.domain.dto.Training;
import com.bescobarres.football.domain.dto.TrainingPlayer;
import com.bescobarres.football.domain.dto.input.TrainingInputDto;
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
    public List<Training> proccess(List<TrainingInputDto> trainingsDto) {
        List<Training> trainings =  trainingMapper.dtoToModel(trainingsDto);
        ifPlayerNotExistCreate(trainings);
        saveTraining(trainings);
        return trainings;
    }

    @Override
    public TrainingPlayer calculate(TrainingPlayer trainingPlayer) {
        return null;
    }

    private void saveTraining(List<Training> trainings) {
        trainings.forEach(training -> {
            training.setId(trainingService.create(training).getId());
        });
    }

    private void ifPlayerNotExistCreate(List<Training> trainings) {
        trainings
                .forEach(training -> {
                training.getPlayer().setId(
                        playerService.create(training.getPlayer()).getId());
        });

    }

}
