package com.bescobarres.football.application.service;

import com.bescobarres.football.domain.dto.TrainingInputDto;
import com.bescobarres.football.domain.dto.TrainingOutputDto;

import java.util.List;

public interface TrainingPlayerService {

    List<TrainingOutputDto> proccess(List<TrainingInputDto> trainingsDto);

}
