package com.example.fifantinyo.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PlayerStatsDto {
    private Long playerId;
    private String playerName;
    private int totalGoals;
    private int totalWins;
    private int totalDraws;
    private int totalLosses;
}
