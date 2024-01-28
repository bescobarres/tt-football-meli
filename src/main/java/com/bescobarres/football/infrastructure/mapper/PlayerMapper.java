package com.bescobarres.football.infrastructure.mapper;

import com.bescobarres.football.domain.dto.PlayerDto;
import com.bescobarres.football.domain.entity.PlayerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

//    @Mappings({
//            @Mapping(source = "id", target = "id"),
//            @Mapping(source = "name", target = "name")
//    })
    PlayerEntity modelToEntity(PlayerDto player);


    PlayerDto entityToModel(PlayerEntity player);


    List<PlayerDto> entityToModel(List<PlayerEntity> players);
}
