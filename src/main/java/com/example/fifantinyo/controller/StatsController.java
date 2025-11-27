package com.example.fifantinyo.controller;

import com.example.fifantinyo.dto.PlayerStatsDto;
import com.example.fifantinyo.enums.Period;
import com.example.fifantinyo.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatsController {

    private final MatchService matchService;

    @GetMapping("/players/period")
    public List<PlayerStatsDto> getPlayerStatsByPeriod(@RequestParam Period period){
        return matchService.getPlayerStatsByPeriod(period);
    }

    @GetMapping("/players/period/champion")
    public PlayerStatsDto getChampion(@RequestParam Period period){
        return matchService.getChampion(period);
    }

    @GetMapping("/players/period/loser")
    public PlayerStatsDto getLoser(@RequestParam Period period){
        return matchService.getLoser(period);
    }

}
