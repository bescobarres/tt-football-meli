package com.bescobarres.football.application.service.impl;

import com.bescobarres.football.application.service.TrainingService;
import com.bescobarres.football.domain.dto.Training;
import com.bescobarres.football.infrastructure.mapper.TrainingMapper;
import com.bescobarres.football.infrastructure.repository.TrainingRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;


@Service
@Validated
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;

    public TrainingServiceImpl(TrainingRepository trainingRepository, TrainingMapper trainingMapper) {
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
    }

    @Override
    public Training create(Training training) {
        return trainingMapper.entityToModel(
                trainingRepository.save(
                        trainingMapper.modelToEntity(training)));
    }

    @Override
    public List<Training> getTrainingsBy(LocalDate week) {
        LocalDate initialDayOfWeek = week.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate lastDayOfWeek = week.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        return trainingMapper.entityToModel(
        trainingRepository.findByDateBetween(initialDayOfWeek, lastDayOfWeek));

    }

}
