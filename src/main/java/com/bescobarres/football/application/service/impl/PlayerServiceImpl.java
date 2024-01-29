package com.bescobarres.football.application.service.impl;

import com.bescobarres.football.application.service.PlayerService;
import com.bescobarres.football.domain.dto.PlayerDto;
import com.bescobarres.football.domain.entity.PlayerEntity;
import com.bescobarres.football.domain.exception.ApiRequestException;
import com.bescobarres.football.infrastructure.mapper.PlayerMapper;
import com.bescobarres.football.infrastructure.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    public PlayerServiceImpl(PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    @Override
    public PlayerDto create(PlayerDto player) {
        PlayerEntity playerEntity;
        if (playerRepository.findById(player.getId()).isPresent()) {
            playerEntity = PlayerEntity.builder().id(player.getId()).name(player.getName()).build();
        } else {
            playerEntity = playerRepository.save(playerMapper.modelToEntity(player));
        }
        return playerMapper.entityToModel(playerEntity);
    }

    @Override
    public List<PlayerDto> getPlayersBy(List<Long> players) {
        return playerMapper.entityToModel(playerRepository.findAllByIdIn(players));
    }
}
