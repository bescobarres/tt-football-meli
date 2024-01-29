package com.bescobarres.football.application.service;

import com.bescobarres.football.domain.dto.TrainingInputDto;

import java.util.List;

public interface TrainingPlayerService {

    void proccess(List<TrainingInputDto> trainingsDto);

}
