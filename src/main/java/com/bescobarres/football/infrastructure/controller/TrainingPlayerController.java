package com.bescobarres.football.infrastructure.controller;

import com.bescobarres.football.application.service.TrainingPlayerService;
import com.bescobarres.football.domain.dto.TrainingOutputDto;
import com.bescobarres.football.domain.dto.TrainingInputDto;
import com.bescobarres.football.domain.exception.ApiRequestException;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class TrainingPlayerController {

    private final TrainingPlayerService trainingPlayerService;

    public TrainingPlayerController(TrainingPlayerService trainingPlayerService){
        this.trainingPlayerService = trainingPlayerService;
    }

    @PostMapping("/training")
    public List<TrainingOutputDto> createTrainingAndPlayer(@Valid @RequestBody List<TrainingInputDto> trainingsDto, BindingResult result) {
        if(result.hasErrors()){
            throw new ApiRequestException(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        }
        return trainingPlayerService.proccess(trainingsDto);
    }

}
