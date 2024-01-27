package com.bescobarres.football.infrastructure.controller;

import com.bescobarres.football.application.service.TrainingPlayerService;
import com.bescobarres.football.domain.dto.Training;
import com.bescobarres.football.domain.dto.TrainingPlayer;
import com.bescobarres.football.domain.dto.input.TrainingInputDto;
import com.bescobarres.football.domain.exception.ApiRequestException;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
public class TrainingPlayerController {

    private final TrainingPlayerService trainingPlayerService;

    public TrainingPlayerController(TrainingPlayerService trainingPlayerService){
        this.trainingPlayerService = trainingPlayerService;
    }

    @PostMapping("/training")
    public List<Training> trainingPlayer(@Valid @RequestBody List<TrainingInputDto> trainingsDto, BindingResult result) {
        if(result.hasErrors()){
            throw new ApiRequestException(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        }
        return trainingPlayerService.proccess(trainingsDto);
    }

}
