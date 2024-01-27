package com.bescobarres.football.application.service;

import com.bescobarres.football.domain.dto.Training;
import com.bescobarres.football.domain.dto.TrainingPlayer;
import com.bescobarres.football.domain.dto.input.TrainingInputDto;

import java.util.List;

public interface TrainingPlayerService {

    List<Training> proccess(List<TrainingInputDto> trainingsDto);


    TrainingPlayer calculate(TrainingPlayer trainingPlayer);
}
