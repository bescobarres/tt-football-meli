package com.bescobarres.football.application.service;

import com.bescobarres.football.domain.dto.TrainingOutputDto;

import java.time.LocalDate;
import java.util.List;

public interface TrainingService {

    TrainingOutputDto create(TrainingOutputDto trainingOutputDto);

    void saveTrainings(List<TrainingOutputDto> trainingsOutputDto);

    List<TrainingOutputDto> getTrainingsByWeek(LocalDate week);
}
