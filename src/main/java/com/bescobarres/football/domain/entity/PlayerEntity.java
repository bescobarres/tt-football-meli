package com.bescobarres.football.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="player")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    @JoinColumn(name = "teamId", insertable = false, updatable = false)
//    @ManyToOne(targetEntity = TeamEntity.class, fetch = FetchType.EAGER)
//    private Team team;
//
//    @Column(name = "teamId")
//    private int teamId;

    @Column
    private String name;

}
