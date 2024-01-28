package com.bescobarres.football.infrastructure.controller;

import com.bescobarres.football.application.service.StartingLineUpTeamService;
import com.bescobarres.football.domain.model.StartingLineUp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class StartingLineUpTeamController {

    private final StartingLineUpTeamService startingLineUpTeamService;

    public StartingLineUpTeamController(StartingLineUpTeamService startingLineUpTeamService) {
        this.startingLineUpTeamService = startingLineUpTeamService;
    }

    @GetMapping("/team")
    public List<StartingLineUp> getStartingLineUpTeam(@RequestParam(value = "day", defaultValue = "#{T(java.time.LocalDate).now()}") LocalDate day,
                                                      @RequestParam(value = "startingLineUpQuantity", defaultValue = "5") int startingLineUpQuantity)  {

        return startingLineUpTeamService.getStartingLineUpPlayers(day, startingLineUpQuantity);
    }
}
