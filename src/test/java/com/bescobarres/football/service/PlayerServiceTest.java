package com.bescobarres.football.service;

import com.bescobarres.football.application.service.impl.PlayerServiceImpl;
import com.bescobarres.football.domain.entity.PlayerEntity;
import com.bescobarres.football.domain.dto.PlayerDto;
import com.bescobarres.football.domain.exception.ApiRequestException;
import com.bescobarres.football.infrastructure.mapper.PlayerMapper;
import com.bescobarres.football.infrastructure.repository.PlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private PlayerMapper playerMapper;

    @InjectMocks
    private PlayerServiceImpl playerService;

//    @Test
//    public void PlayerService_CreatePlayer_ReturnException(){
//        PlayerDto player = PlayerDto.builder().id(1L).name("PLayer 1").build();
//        String expectedMessage = "Player with id: 1 doesn't exist";
//
//        when(playerRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
//
//        Exception exception = Assertions.assertThrows(ApiRequestException.class, () ->
//            playerService.create(player)
//        );
//
//        Assertions.assertEquals(expectedMessage, exception.getMessage());
//
//
//    }

    @Test
    public void PlayerService_CreatePlayer_ReturnPlayerSaved(){
        PlayerEntity playerEntityMock = PlayerEntity.builder().id(1L).name("Player 1").build();
        PlayerDto player = PlayerDto.builder().name("PLayer 1").build();
        PlayerDto playerDtoSavedExpected = PlayerDto.builder().id(1L).name("PLayer 1").build();

        when(playerRepository.save(playerEntityMock)).thenReturn(playerEntityMock);
        when(playerMapper.modelToEntity(player)).thenReturn(playerEntityMock);
        when(playerMapper.entityToModel(playerEntityMock)).thenReturn(playerDtoSavedExpected);

        PlayerDto playerDtoSaved = playerService.create(player);

        Assertions.assertEquals(playerEntityMock.getId(), playerDtoSaved.getId());

    }


    @Test
    public void PlayerService_FindPlayers_ReturnPlayers(){
        PlayerEntity playerEntityMock = PlayerEntity.builder().id(1L).name("Player 1").build();
        PlayerDto playerDtoExpected = PlayerDto.builder().id(1L).name("PLayer 1").build();


        when(playerRepository.findAllByIdIn(anyList())).thenReturn(List.of(playerEntityMock));
        when(playerMapper.entityToModel(anyList())).thenReturn(List.of(playerDtoExpected));

        List<PlayerDto> playersDto = playerService.getPlayersBy(List.of(1L));

        Assertions.assertFalse(playersDto.isEmpty());
        Assertions.assertTrue(playersDto.stream().anyMatch(x -> x.getId().equals(playerDtoExpected.getId())));

    }

}
