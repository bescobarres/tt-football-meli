package com.bescobarres.football.application.service;

import com.bescobarres.football.domain.model.StartingLineUp;

import java.time.LocalDate;
import java.util.List;

public interface StartingLineUpTeamService {

    List<StartingLineUp> getStartingLineUpPlayers(LocalDate week, int startingLineUpQuantity, int minimumTrainingsByWeek);
}
