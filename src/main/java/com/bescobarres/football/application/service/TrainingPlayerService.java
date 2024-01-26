package com.bescobarres.football.application.service;

import com.bescobarres.football.domain.dto.TrainingPlayer;

public interface TrainingPlayerService {

    TrainingPlayer proccess(TrainingPlayer trainingPlayer);


    TrainingPlayer calculate(TrainingPlayer trainingPlayer);
}
