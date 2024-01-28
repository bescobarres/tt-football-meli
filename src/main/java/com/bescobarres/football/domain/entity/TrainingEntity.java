package com.bescobarres.football.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "training")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JoinColumn(name = "playerId",
            insertable = false,
            updatable = false)
    @ManyToOne(targetEntity = PlayerEntity.class, fetch = FetchType.EAGER)
    private PlayerEntity player;

    @Column(name = "playerId")
    private long playerId;

    @Column
    private double power;

    @Column
    private double distance;

    @Column
    private long time;

    @Column
    private long passes;

    @Column
    private double speed;

    @Column
    private double score;

    @Column
    private LocalDate date;

}
