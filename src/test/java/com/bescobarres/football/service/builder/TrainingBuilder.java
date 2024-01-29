package com.bescobarres.football.service.builder;

import com.bescobarres.football.domain.dto.PlayerDto;
import com.bescobarres.football.domain.dto.TrainingInputDto;
import com.bescobarres.football.domain.dto.TrainingOutputDto;
import com.bescobarres.football.domain.model.Stats;

import java.time.LocalDate;
import java.util.List;

public class TrainingBuilder {
    public static List<TrainingOutputDto> getBuildThreeTrainingsByFivePlayer() {

        Stats stats = Stats.builder().build();
        stats.setDistance(100D);
        stats.setTime(50L);
        stats.setPasses(4L);
        stats.setPower(30D);

        return List.of(TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(1L).name("Player 1").build())
                        .stats(stats)
                        .date(LocalDate.now())
                        .build(),
                TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(2L).name("Player 2").build())
                        .stats(Stats.builder()
                                .distance(stats.getDistance())
                                .passes(stats.getPasses())
                                .power(stats.getPower())
                                .time(stats.getTime()).build())
                        .build(),
                TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(3L).name("Player 3").build())
                        .stats(Stats.builder()
                                .distance(100D)
                                .passes(20L)
                                .power(100D)
                                .time(50L).build())
                        .build(),
                TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(4L).name("Player 4").build())
                        .stats(Stats.builder()
                                .distance(100D)
                                .passes(20L)
                                .power(100D)
                                .time(50L).build())
                        .build(),
                TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(5L).name("Player 5").build())
                        .stats(Stats.builder()
                                .distance(100D)
                                .passes(20L)
                                .power(100D)
                                .time(50L).build())
                        .build(),
                TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(1L).name("Player 1").build())
                        .stats(Stats.builder()
                                .distance(100D)
                                .passes(20L)
                                .power(100D)
                                .time(50L).build())
                        .build(),
                TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(2L).name("Player 2").build())
                        .stats(Stats.builder()
                                .distance(100D)
                                .passes(20L)
                                .power(100D)
                                .time(50L).build())
                        .build(),
                TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(3L).name("Player 3").build())
                        .stats(Stats.builder()
                                .distance(100D)
                                .passes(20L)
                                .power(100D)
                                .time(50L).build())
                        .build(),
                TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(4L).name("Player 4").build())
                        .stats(Stats.builder()
                                .distance(100D)
                                .passes(20L)
                                .power(100D)
                                .time(50L).build())
                        .build(),
                TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(5L).name("Player 5").build())
                        .stats(Stats.builder()
                                .distance(100D)
                                .passes(20L)
                                .power(100D)
                                .time(50L).build())
                        .build(),
                TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(1L).name("Player 1").build())
                        .stats(Stats.builder()
                                .distance(100D)
                                .passes(20L)
                                .power(100D)
                                .time(50L).build())
                        .build(),
                TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(2L).name("Player 2").build())
                        .stats(Stats.builder()
                                .distance(100D)
                                .passes(20L)
                                .power(100D)
                                .time(50L).build())
                        .build(),
                TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(3L).name("Player 3").build())
                        .stats(Stats.builder()
                                .distance(100D)
                                .passes(20L)
                                .power(100D)
                                .time(50L).build())
                        .build(),
                TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(4L).name("Player 4").build())
                        .stats(Stats.builder()
                                .distance(100D)
                                .passes(20L)
                                .power(100D)
                                .time(50L).build())
                        .build(),
                TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(5L).name("Player 5").build())
                        .stats(Stats.builder()
                                .distance(100D)
                                .passes(20L)
                                .power(100D)
                                .time(50L).build())
                        .build());
    }

    public static List<TrainingOutputDto> getBuildTwoTrainingsByFivePlayer() {
        return List.of(TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(1L).name("Player 1").build())
                        .stats(Stats.builder()
                                .distance(100D)
                                .passes(20L)
                                .power(100D)
                                .time(50L)
                                .build())
                        .date(LocalDate.now())
                        .build(),
                TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(2L).name("Player 2").build())
                        .stats(Stats.builder()
                                .distance(100D)
                                .passes(20L)
                                .power(100D)
                                .time(50L).build())
                        .build(),
                TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(3L).name("Player 3").build())
                        .stats(Stats.builder()
                                .distance(100D)
                                .passes(20L)
                                .power(100D)
                                .time(50L).build())
                        .build(),
                TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(4L).name("Player 4").build())
                        .stats(Stats.builder()
                                .distance(100D)
                                .passes(20L)
                                .power(100D)
                                .time(50L).build())
                        .build(),
                TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(5L).name("Player 5").build())
                        .stats(Stats.builder()
                                .distance(100D)
                                .passes(20L)
                                .power(100D)
                                .time(50L).build())
                        .build(),
                TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(1L).name("Player 1").build())
                        .stats(Stats.builder()
                                .distance(100D)
                                .passes(20L)
                                .power(100D)
                                .time(50L).build())
                        .build(),
                TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(2L).name("Player 2").build())
                        .stats(Stats.builder()
                                .distance(100D)
                                .passes(20L)
                                .power(100D)
                                .time(50L).build())
                        .build(),
                TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(3L).name("Player 3").build())
                        .stats(Stats.builder()
                                .distance(100D)
                                .passes(20L)
                                .power(100D)
                                .time(50L).build())
                        .build(),
                TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(4L).name("Player 4").build())
                        .stats(Stats.builder()
                                .distance(100D)
                                .passes(20L)
                                .power(100D)
                                .time(50L).build())
                        .build(),
                TrainingOutputDto.builder()
                        .player(PlayerDto.builder().id(5L).name("Player 5").build())
                        .stats(Stats.builder()
                                .distance(100D)
                                .passes(20L)
                                .power(100D)
                                .time(50L).build())
                        .build());
    }


    public static List<TrainingInputDto> getBuildTrainingsInput() {
        return List.of(TrainingInputDto.builder()
                        .playerId(1L).name("Player 1")
                        .distance(100D)
                        .passes(20L)
                        .power(100D)
                        .time(50L)
                        .date(LocalDate.now()).build(),
                TrainingInputDto.builder()
                        .playerId(1L).name("Player 2")
                        .distance(100D)
                        .passes(20L)
                        .power(100D)
                        .time(50L)
                        .date(LocalDate.now()).build(),
                TrainingInputDto.builder()
                        .playerId(1L).name("Player 3")
                        .distance(100D)
                        .passes(20L)
                        .power(100D)
                        .time(50L)
                        .date(LocalDate.now()).build(),
                TrainingInputDto.builder()
                        .playerId(1L).name("Player 4")
                        .distance(100D)
                        .passes(20L)
                        .power(100D)
                        .time(50L)
                        .date(LocalDate.now()).build(),
                TrainingInputDto.builder()
                        .playerId(1L).name("Player 5")
                        .distance(100D)
                        .passes(20L)
                        .power(100D)
                        .time(50L)
                        .date(LocalDate.now()).build(),
                TrainingInputDto.builder()
                        .playerId(1L).name("Player 6")
                        .distance(100D)
                        .passes(20L)
                        .power(100D)
                        .time(50L)
                        .date(LocalDate.now()).build(),
                TrainingInputDto.builder()
                        .playerId(1L).name("Player 7")
                        .distance(100D)
                        .passes(20L)
                        .power(100D)
                        .time(50L)
                        .date(LocalDate.now()).build(),
                TrainingInputDto.builder()
                        .playerId(1L).name("Player 8")
                        .distance(100D)
                        .passes(20L)
                        .power(100D)
                        .time(50L)
                        .date(LocalDate.now()).build(),
                TrainingInputDto.builder()
                        .playerId(1L).name("Player 9")
                        .distance(100D)
                        .passes(20L)
                        .power(100D)
                        .time(50L)
                        .date(LocalDate.now()).build());
    }
}