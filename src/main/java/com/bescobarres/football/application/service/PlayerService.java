package com.bescobarres.football.application.service;

import com.bescobarres.football.domain.dto.PlayerDto;

import java.util.List;

public interface PlayerService {

    PlayerDto create(PlayerDto player);

    List<PlayerDto> getPlayersBy(List<Long> id);
}
