package com.example.fifantinyo.mapper;

import com.example.fifantinyo.dto.MatchResponseDto;
import com.example.fifantinyo.model.Match;

public class MatchMapper {
    public static MatchResponseDto toDto(Match match){
        return MatchResponseDto.builder()
                .id(match.getId())
                .date(match.getDate().toString())
                .player1Id(match.getPlayer1().getId())
                .player2Id(match.getPlayer2().getId())
                .player1Name(match.getPlayer1().getName())
                .player2Name(match.getPlayer2().getName())
                .scorePlayer1(match.getScore1())
                .scorePlayer2(match.getScore2())
                .build();

    }
}
