package com.bescobarres.football.domain.dto;

import com.bescobarres.football.domain.model.Stats;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingOutputDto {

    private Long id;
    private PlayerDto player;
    private Stats stats;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

}
