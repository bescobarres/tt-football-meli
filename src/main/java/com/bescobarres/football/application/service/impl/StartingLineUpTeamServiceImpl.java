package com.bescobarres.football.application.service.impl;

import com.bescobarres.football.application.service.StartingLineUpTeamService;
import com.bescobarres.football.application.service.TrainingService;
import com.bescobarres.football.domain.dto.Training;
import com.bescobarres.football.domain.exception.ApiRequestException;
import com.bescobarres.football.domain.exception.ApiRequestExceptionNotFound;
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
    public List<StartingLineUp> getStartingLineUpPlayers(LocalDate week, int startingLineUpQuantity, int minimumTrainingsByWeek) {

        List<Training> trainings = trainingService.getTrainingsBy(week);

        Map<Player, List<Training>> trainingsByPlayer = getTrainingsByPlayerListMap(trainings);

        List<Player> playersWithMinimumThreeTrainings = getPlayersWithMinimumThreeTrainings(minimumTrainingsByWeek, trainingsByPlayer);

        validateMinimunTrainings(playersWithMinimumThreeTrainings);

        List<StartingLineUp> scorePlayersAverage = getStartingLineUpsScoreAverage(trainingsByPlayer, playersWithMinimumThreeTrainings);

        return getPlayersWithBestScoreAverage(startingLineUpQuantity, scorePlayersAverage);
    }

    private static void validateMinimunTrainings(List<Player> playersWithMinimumThreeTrainings) {
        if(playersWithMinimumThreeTrainings.isEmpty()){
            throw new ApiRequestExceptionNotFound("No hay suficiente información");
        }
    }

    private static List<StartingLineUp> getPlayersWithBestScoreAverage(int startingLineUpQuantity, List<StartingLineUp> scorePlayersAverage) {
        return scorePlayersAverage.stream()
                .sorted(Comparator.comparingDouble(StartingLineUp::getScore).reversed())
                .limit(startingLineUpQuantity)
                .toList();
    }

    private static List<StartingLineUp> getStartingLineUpsScoreAverage(Map<Player, List<Training>> trainingsByPlayer, List<Player> playersWithMinimumThreeTrainings) {
        List<StartingLineUp> scorePlayersAverage = playersWithMinimumThreeTrainings.stream()
                .map(player -> {
                    List<Training> trainingsPlayer = trainingsByPlayer.get(player);
                    double scoreAverage = trainingsPlayer.stream()
                            .mapToDouble(x -> x.getStats().getScore())
                            .average()
                            .orElse(0);
                    return new StartingLineUp(player, scoreAverage);
                })
                .toList();
        return scorePlayersAverage;
    }

    private static List<Player> getPlayersWithMinimumThreeTrainings(int minimumTrainingsByWeek, Map<Player, List<Training>> trainingsByPlayer) {
        return trainingsByPlayer.entrySet().stream()
                .filter(entry -> entry.getValue().size() >= minimumTrainingsByWeek)
                .map(Map.Entry::getKey)
                .toList();
    }

    private static Map<Player, List<Training>> getTrainingsByPlayerListMap(List<Training> trainings) {
        return trainings.stream()
                .collect(Collectors.groupingBy(Training::getPlayer));
    }

}
