package com.bescobarres.football.infrastructure.repository;

import com.bescobarres.football.domain.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

    Optional<PlayerEntity> findById(Long id);

    List<PlayerEntity> findAllByIdIn(List<Long> players);

}
