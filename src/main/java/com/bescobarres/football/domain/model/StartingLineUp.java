package com.bescobarres.football.domain.model;

import com.bescobarres.football.domain.dto.PlayerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StartingLineUp {
    private PlayerDto player;
    private Double score;
}
