package com.bescobarres.football.application.service.impl;

import com.bescobarres.football.application.service.PlayerService;
import com.bescobarres.football.application.service.StartingLineUpTeamService;
import com.bescobarres.football.application.service.TrainingService;
import com.bescobarres.football.domain.dto.PlayerDto;
import com.bescobarres.football.domain.dto.TrainingOutputDto;
import com.bescobarres.football.domain.exception.ApiRequestExceptionNotFound;
import com.bescobarres.football.domain.model.StartingLineUp;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StartingLineUpTeamServiceImpl implements StartingLineUpTeamService {

    private static final int MINIMUM_QUANTITY_TRAINING_BY_WEEK = 3;

    private final TrainingService trainingService;

    private final PlayerService playerService;

    public StartingLineUpTeamServiceImpl(TrainingService trainingService, PlayerService playerService) {
        this.trainingService = trainingService;
        this.playerService = playerService;
    }

    @Override
    public List<StartingLineUp> getStartingLineUpPlayers(LocalDate day, int startingLineUpQuantity) {

        List<TrainingOutputDto> trainingsOutputDto = trainingService.getTrainingsByWeek(day);

        Map<PlayerDto, List<TrainingOutputDto>> trainingsByPlayer = getTrainingsByPlayerListMap(trainingsOutputDto);

        List<PlayerDto> playersWithMinimumThreeTrainings = getPlayersWithMinimumThreeTrainings(trainingsByPlayer);

        validateMinimumTrainingsAndPlayers(playersWithMinimumThreeTrainings, startingLineUpQuantity);

        List<StartingLineUp> scorePlayersAverage = getStartingLineUpsScoreAverage(trainingsByPlayer, playersWithMinimumThreeTrainings);

        return getPlayersWithBestScoreAverage(startingLineUpQuantity, scorePlayersAverage);
    }

    private static void validateMinimumTrainingsAndPlayers(List<PlayerDto> playersWithMinimumThreeTrainings, int startingLineUpQuantity) {
        if (playersWithMinimumThreeTrainings.isEmpty() || playersWithMinimumThreeTrainings.size() < startingLineUpQuantity) {
            throw new ApiRequestExceptionNotFound("No hay suficiente información");
        }
    }

    private List<StartingLineUp> getPlayersWithBestScoreAverage(int startingLineUpQuantity, List<StartingLineUp> scorePlayersAverage) {
        List<Long> playersId = scorePlayersAverage.stream()
                .map(x -> x.getPlayer().getId()).toList();

        playerService.getPlayersBy(playersId).forEach(player -> {
            scorePlayersAverage.stream().filter(x -> x.getPlayer().getId().equals(player.getId()))
                    .findFirst().ifPresent(x -> {
                        x.getPlayer().setName(player.getName());
                    });
        });

        return scorePlayersAverage.stream()
                .sorted(Comparator.comparingDouble(StartingLineUp::getScore).reversed())
                .limit(startingLineUpQuantity)
                .toList();
    }

    private static List<StartingLineUp> getStartingLineUpsScoreAverage(Map<PlayerDto, List<TrainingOutputDto>> trainingsByPlayer, List<PlayerDto> playersWithMinimumThreeTrainings) {
        return playersWithMinimumThreeTrainings.stream()
                .map(player -> {
                    List<TrainingOutputDto> trainingsPlayer = trainingsByPlayer.get(player);
                    double scoreAverage = trainingsPlayer.stream()
                            .mapToDouble(x -> x.getStats().getScore())
                            .average()
                            .orElse(0);
                    return new StartingLineUp(player, scoreAverage);
                })
                .toList();
    }

    private static List<PlayerDto> getPlayersWithMinimumThreeTrainings(Map<PlayerDto, List<TrainingOutputDto>> trainingsByPlayer) {
        return trainingsByPlayer.entrySet().stream()
                .filter(entry -> entry.getValue().size() >= MINIMUM_QUANTITY_TRAINING_BY_WEEK)
                .map(Map.Entry::getKey)
                .toList();
    }

    private static Map<PlayerDto, List<TrainingOutputDto>> getTrainingsByPlayerListMap(List<TrainingOutputDto> trainingsOutputDto) {
        return trainingsOutputDto.stream()
                .collect(Collectors.groupingBy(TrainingOutputDto::getPlayer));
    }

}
