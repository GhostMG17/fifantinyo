package com.example.fifantinyo.service;
import com.example.fifantinyo.dto.MatchRequestDto;
import com.example.fifantinyo.dto.MatchResponseDto;
import com.example.fifantinyo.dto.PlayerStatsDto;
import com.example.fifantinyo.enums.Period;

import java.time.LocalDate;
import java.util.List;

public interface MatchService {
    MatchResponseDto createMatch(MatchRequestDto matchRequestDto);
    MatchResponseDto getMatchById(Long id);
    List<MatchResponseDto> getAllMatches();
    List<MatchResponseDto> getAllMatchesByPlayer(Long playerId);
    List<MatchResponseDto> getMatchesByDateRange(LocalDate start, LocalDate end);
    void deleteMatch(Long id);
    List<PlayerStatsDto> getPlayerStatsByPeriod(Period period);
    PlayerStatsDto getChampion(Period period);
    PlayerStatsDto getLoser(Period period);
}

