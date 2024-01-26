package com.bescobarres.football.application.service.impl;

import com.bescobarres.football.application.service.StartingLineUpTeamService;
import com.bescobarres.football.application.service.TrainingService;
import com.bescobarres.football.domain.dto.Training;
import com.bescobarres.football.domain.model.Player;
import com.bescobarres.football.domain.model.StartingLineUp;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StartingLineUpTeamServiceImpl implements StartingLineUpTeamService {

    private final TrainingService trainingService;

    public StartingLineUpTeamServiceImpl(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @Override
    public List<StartingLineUp> getStartingLineUpPlayers(LocalDate week) {

        List<Training> trainings = trainingService.getTrainingsBy(week);

        Map<Player, List<Training>> trainingsByPlayer = trainings.stream()
                .collect(Collectors.groupingBy(Training::getPlayer));

        List<Player> playersWith3Trainings = trainingsByPlayer.entrySet().stream()
                .filter(entry -> entry.getValue().size() >= 3)
                .map(Map.Entry::getKey)
                .toList();

        List<StartingLineUp> scorePlayersAverage = playersWith3Trainings.stream()
                .map(player -> {
                    List<Training> trainingsPlayer = trainingsByPlayer.get(player);
                    double scoreAverage = trainingsPlayer.stream()
                            .mapToDouble(x -> x.getStats().getScore())
                            .average()
                            .orElse(0);
                    return new StartingLineUp(player, scoreAverage);
                })
                .toList();

        return scorePlayersAverage.stream()
                .sorted(Comparator.comparingDouble(StartingLineUp::getScore).reversed())
                .limit(5)
                .toList();

    }

}
