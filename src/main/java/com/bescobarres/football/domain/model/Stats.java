package com.bescobarres.football.domain.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stats {

    private static final int PERCENTAGE = 100;
    private static final int VARIABLES_QUANTITY = 3;

    @NotEmpty(message = "")
    @DecimalMin(message = "Debe tener un valor en potencia mayor a 0", value = "0")
    @Negative
    private Double power;

    @NotEmpty(message = "")
    @DecimalMin(message = "Debe tener un valor en distancia mayor a 0", value = "0")
    @Negative
    private Double distance;

    @NotEmpty(message = "")
    @DecimalMin(message = "Debe tener un valor en tiempo en segundos mayor a 0", value = "0")
    @Negative
    private Long time;

    @NotEmpty(message = "")
    @DecimalMin(message = "Debe tener un valor en pases exitosos mayor a 0", value = "0")
    @Negative
    private Long passes;

    @Getter(lazy = true) private final Double speed = calculateSpeed();

    @Getter(lazy = true) private final Double score = calculateScore();

    private Double calculateSpeed(){
        return (distance / time) * PERCENTAGE;
    }
    private Double calculateScore(){
        return (power + calculateSpeed() + passes) / VARIABLES_QUANTITY ;
    }
}
