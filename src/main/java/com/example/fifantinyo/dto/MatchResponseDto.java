package com.example.fifantinyo.dto;


import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class MatchResponseDto {
    private Long id;
    private String date;

    private Long player1Id;
    private Long player2Id;

    private String player1Name;
    private String player2Name;

    private int scorePlayer1;
    private int scorePlayer2;
}
