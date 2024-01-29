package com.bescobarres.football.repository;

import com.bescobarres.football.domain.entity.PlayerEntity;
import com.bescobarres.football.infrastructure.repository.PlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Objects;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    PlayerEntity playerEntity;

    @BeforeEach
    public void initializeDatabaseForPlayer(){
        playerEntity = PlayerEntity.builder().id(20L).name("Test").build();
        playerRepository.save(playerEntity);
    }

    @Test
    public void PlayerRepository_findPlayerById_ReturnPlayer(){

        Long playerId = playerEntity.getId();
        String expectedName = playerEntity.getName();

        PlayerEntity playerEntityTest = playerRepository.findById(playerId).orElse(null);

        assert playerEntityTest != null;
        Assertions.assertEquals(expectedName, playerEntityTest.getName());
    }

    @Test
    public void PlayerRepository_findPlayerByIdIn_ReturnPlayers(){
        Long playerId = playerEntity.getId();
        String expectedName = playerEntity.getName();

        List<PlayerEntity> playerEntityTest = playerRepository.findAllByIdIn(List.of(playerId));

        assert playerEntityTest != null;
        Assertions.assertFalse(playerEntityTest.isEmpty());
        Assertions.assertEquals(expectedName, Objects.requireNonNull(playerEntityTest
                .stream().filter(x -> x.getName().equals(expectedName)).findFirst().orElse(null)).getName());
        assert playerEntityTest.size() > 0;
    }

}
