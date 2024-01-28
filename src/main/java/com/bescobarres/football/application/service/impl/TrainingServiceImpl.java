package com.bescobarres.football.application.service.impl;

import com.bescobarres.football.application.service.TrainingService;
import com.bescobarres.football.domain.dto.TrainingOutputDto;
import com.bescobarres.football.infrastructure.mapper.TrainingMapper;
import com.bescobarres.football.infrastructure.repository.TrainingRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;

    public TrainingServiceImpl(TrainingRepository trainingRepository, TrainingMapper trainingMapper) {
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
    }

    @Override
    public TrainingOutputDto create(TrainingOutputDto trainingOutputDto) {
        return trainingMapper.entityToModel(
                trainingRepository.save(
                        trainingMapper.modelToEntity(trainingOutputDto)));
    }

    @Override
    public void saveTrainings(List<TrainingOutputDto> trainingsOutputDto) {
        trainingsOutputDto.forEach(training -> {
            training.setId(create(training).getId());
        });
    }

    @Override
    public List<TrainingOutputDto> getTrainingsByWeek(LocalDate dayOfWeek) {
        LocalDate initialDayOfWeek = dayOfWeek.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate lastDayOfWeek = dayOfWeek.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        return trainingMapper.entityToModel(
                trainingRepository.findByDateBetween(initialDayOfWeek, lastDayOfWeek));

    }

}
