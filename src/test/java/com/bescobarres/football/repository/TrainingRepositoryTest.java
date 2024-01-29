package com.bescobarres.football.repository;


import com.bescobarres.football.domain.entity.PlayerEntity;
import com.bescobarres.football.domain.entity.TrainingEntity;
import com.bescobarres.football.infrastructure.repository.PlayerRepository;
import com.bescobarres.football.infrastructure.repository.TrainingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TrainingRepositoryTest {


    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private PlayerRepository playerRepository;


    PlayerEntity playerEntity;

    @BeforeEach
    public void initializeDatabaseForPlayer(){
        playerEntity = PlayerEntity.builder().name("Test").build();
        playerRepository.save(playerEntity);

    }


    @Test
    public void TrainingRepository_SaveRepository_ReturnTrainingSaved(){
        //Arrange

        TrainingEntity trainingEntity = TrainingEntity.builder()
                .player(playerEntity)
                .playerId(playerEntity.getId())
                .date(LocalDate.now())
                .power(1D)
                .score(20D)
                .speed(30D)
                .passes(20L)
                .distance(20D)
                .build();
        Long expectedPlayerId = trainingEntity.getPlayerId();
        Double expectedDistance = trainingEntity.getDistance();
        Double expectedPower = trainingEntity.getPower();
        Double expectedScore = trainingEntity.getScore();
        Double expectedSpeed = trainingEntity.getSpeed();
        Long expectedPasses = trainingEntity.getPasses();
        Long expectedTime = trainingEntity.getTime();
        LocalDate expectedDate = trainingEntity.getDate();

        //Act
        TrainingEntity trainingEntitySaved = trainingRepository.save(trainingEntity);

        //Assert
        Assertions.assertEquals(expectedPlayerId, trainingEntitySaved.getPlayerId());
        Assertions.assertEquals(expectedDistance, trainingEntitySaved.getDistance());
        Assertions.assertEquals(expectedPower, trainingEntitySaved.getPower());
        Assertions.assertEquals(expectedScore, trainingEntitySaved.getScore());
        Assertions.assertEquals(expectedSpeed, trainingEntitySaved.getSpeed());
        Assertions.assertEquals(expectedPasses, trainingEntitySaved.getPasses());
        Assertions.assertEquals(expectedPasses, trainingEntitySaved.getPasses());
        Assertions.assertEquals(expectedTime, trainingEntitySaved.getTime());
        Assertions.assertEquals(expectedDate, trainingEntitySaved.getDate());
    }

}
