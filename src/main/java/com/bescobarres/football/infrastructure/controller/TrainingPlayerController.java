package com.bescobarres.football.infrastructure.controller;

import com.bescobarres.football.application.service.TrainingPlayerService;
import com.bescobarres.football.domain.dto.TrainingPlayer;
import com.bescobarres.football.domain.exception.ApiRequestException;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class TrainingPlayerController {

    private final TrainingPlayerService trainingPlayerService;

    public TrainingPlayerController(TrainingPlayerService trainingPlayerService){
        this.trainingPlayerService = trainingPlayerService;
    }

    @PostMapping("/training")
    public TrainingPlayer trainingPlayer(@Valid @RequestBody TrainingPlayer trainingPlayer, BindingResult result) throws Exception {
        if(result.hasErrors()){
            throw new ApiRequestException(result.getAllErrors().toString());
        }
        return trainingPlayerService.proccess(trainingPlayer);
    }

}
