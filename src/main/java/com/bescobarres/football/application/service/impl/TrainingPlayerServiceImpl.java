package com.bescobarres.football.application.service.impl;

import com.bescobarres.football.application.service.PlayerService;
import com.bescobarres.football.application.service.TrainingPlayerService;
import com.bescobarres.football.application.service.TrainingService;
import com.bescobarres.football.domain.dto.Training;
import com.bescobarres.football.domain.dto.TrainingPlayer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingPlayerServiceImpl implements TrainingPlayerService {

    private final PlayerService playerService;
    private final TrainingService trainingService;

    public TrainingPlayerServiceImpl(PlayerService playerService, TrainingService trainingService) {
        this.playerService = playerService;
        this.trainingService = trainingService;
    }

    @Override
    public TrainingPlayer proccess(TrainingPlayer trainingPlayer) {
        ifPlayerNotExistCreate(trainingPlayer.getTrainings());
        saveTraining(trainingPlayer.getTrainings());
        return trainingPlayer;
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
        trainings.stream().filter(x -> x.getPlayer().getId() == null || x.getPlayer().getId() == 0 )
                .forEach(training -> {
                training.getPlayer().setId(
                        playerService.create(training.getPlayer()).getId());
        });

    }

}
