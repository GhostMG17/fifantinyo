package com.example.fifantinyo.mapper;

import com.example.fifantinyo.dto.PlayerResponseDto;
import com.example.fifantinyo.model.Player;

public class PlayerMapper {
    public static PlayerResponseDto toDto(Player player){
        return PlayerResponseDto.builder()
                .id(player.getId())
                .name(player.getName())
                .build();
    }
}
