package com.bescobarres.football.application.service;

import com.bescobarres.football.domain.dto.Training;

import java.time.LocalDate;
import java.util.List;

public interface TrainingService {

    Training create(Training training);

    List<Training> getTrainingsBy(LocalDate week);
}
