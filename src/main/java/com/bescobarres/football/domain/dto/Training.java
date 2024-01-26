package com.bescobarres.football.domain.dto;

import com.bescobarres.football.domain.model.Player;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Valid
public class Training {

    private Long id;
    @NotNull
    private Player player;
    private Stats stats;

    @NotNull(message = "El entrenamiento debe tener una fecha en el formato yyyy-MM-dd ")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;



}
