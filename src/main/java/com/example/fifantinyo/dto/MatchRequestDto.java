package com.example.fifantinyo.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class MatchRequestDto {
    private Long player1Id;
    private Long player2Id;
    private int scorePlayer1;
    private int scorePlayer2;
    private String date; // ISO: yyyy-MM-dd
}