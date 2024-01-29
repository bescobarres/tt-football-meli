package com.bescobarres.football.domain.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stats {

    private static final int PERCENTAGE = 100;
    private static final int VARIABLES_QUANTITY = 3;

    private Double power;

    private Double distance;

    private Long time;

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
