package com.bescobarres.football.application.service.impl;

import com.bescobarres.football.application.service.PlayerService;
import com.bescobarres.football.domain.entity.PlayerEntity;
import com.bescobarres.football.domain.exception.ApiRequestException;
import com.bescobarres.football.domain.model.Player;
import com.bescobarres.football.infrastructure.mapper.PlayerMapper;
import com.bescobarres.football.infrastructure.repository.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl  implements PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    public PlayerServiceImpl(PlayerRepository playerRepository, PlayerMapper playerMapper){
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    @Override
    public Player create(Player player) {
        PlayerEntity playerEntity;
        if(player.getId() != null){
            playerEntity = playerRepository.findById(player.getId())
                    .orElseThrow(() -> new ApiRequestException("Player with id: " + player.getId() + " doesnt exist"));
        }else {
            playerEntity = playerRepository.save(playerMapper.modelToEntity(player));
        }
        return playerMapper.entityToModel(playerEntity);
    }
}
