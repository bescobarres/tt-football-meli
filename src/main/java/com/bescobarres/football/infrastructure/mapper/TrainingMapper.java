package com.bescobarres.football.infrastructure.mapper;

import com.bescobarres.football.domain.dto.TrainingOutputDto;
import com.bescobarres.football.domain.dto.TrainingInputDto;
import com.bescobarres.football.domain.entity.TrainingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrainingMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "player.id", target = "playerId"),
            @Mapping(source = "stats.power", target = "power"),
            @Mapping(source = "stats.distance", target = "distance"),
            @Mapping(source = "stats.time", target = "time"),
            @Mapping(source = "stats.passes", target = "passes"),
            @Mapping(source = "stats.speed", target = "speed"),
            @Mapping(source = "stats.score", target = "score"),
            @Mapping(source = "date", target = "date")
    })
    TrainingEntity modelToEntity(TrainingOutputDto trainingOutputDto);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "playerId", target = "player.id"),
            @Mapping(source = "power", target = "stats.power"),
            @Mapping(source = "distance", target = "stats.distance"),
            @Mapping(source = "passes", target = "stats.passes"),
            @Mapping(source = "time", target = "stats.time"),
            @Mapping(source = "date", target = "date"),
    })
    TrainingOutputDto entityToModel(TrainingEntity training);

    List<TrainingEntity> modelToEntity(List<TrainingOutputDto> trainingOutputDto);


    List<TrainingOutputDto> entityToModel(List<TrainingEntity> training);

    @Mappings({
            @Mapping(source = "playerId", target = "player.id"),
            @Mapping(source = "name", target = "player.name"),
            @Mapping(source = "power", target = "stats.power"),
            @Mapping(source = "distance", target = "stats.distance"),
            @Mapping(source = "time", target = "stats.time"),
            @Mapping(source = "passes", target = "stats.passes"),
            @Mapping(source = "date", target = "date"),
    })
    TrainingOutputDto dtoToModel(TrainingInputDto trainingDto);

    List<TrainingOutputDto> dtoToModel(List<TrainingInputDto> trainingsDto);

}
