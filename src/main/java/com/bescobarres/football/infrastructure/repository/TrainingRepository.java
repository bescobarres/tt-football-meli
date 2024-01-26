package com.bescobarres.football.infrastructure.repository;

import com.bescobarres.football.domain.entity.TrainingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<TrainingEntity, Long> {

    List<TrainingEntity> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
