package com.bescobarres.football.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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
public class TrainingInputDto {

    private Long playerId;

    private String name;

    @DecimalMin(value = "0.1", message = "El campo power debe ser un valor mayor a 0")
    @Max(value = 100, message = "El campo power debe ser un valor entre 0 y 100")
    @NotNull(message = "El campo power no debe ser NULL ")
    private Double power;

    @DecimalMin(value = "0.1", message = "El campo distance debe ser un valor mayor a 0")
    @NotNull(message = "El campo distance no debe ser NULL")
    private Double distance;

    @Min(value = 1, message = "El campo time debe ser un valor mayor a 0")
    @NotNull(message = "El campo time no debe ser NULL")
    private Long time;

    @Min(value = 1, message = "El campo passes debe ser un valor mayor a 0")
    @Max(value = 100, message = "El campo passes debe ser un valor entre 0 y 100")
    @NotNull(message = "El campo passes no debe ser NULL")
    private Long passes;

    @NotNull(message = "El entrenamiento debe tener una fecha en el formato yyyy-MM-dd ")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate date;

}
