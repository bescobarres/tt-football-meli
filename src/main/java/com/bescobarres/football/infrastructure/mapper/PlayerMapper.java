package com.bescobarres.football.infrastructure.mapper;

import com.bescobarres.football.domain.entity.PlayerEntity;
import com.bescobarres.football.domain.model.Player;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

//    @Mappings({
//            @Mapping(source = "id", target = "id"),
//            @Mapping(source = "name", target = "name")
//    })
    PlayerEntity modelToEntity(Player player);


    Player entityToModel(PlayerEntity player);
}
