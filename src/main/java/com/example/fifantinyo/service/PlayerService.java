package com.example.fifantinyo.service;

import com.example.fifantinyo.dto.PlayerRequestDto;
import com.example.fifantinyo.dto.PlayerResponseDto;
import com.example.fifantinyo.model.Player;

import java.util.List;
import java.util.UUID;

public interface PlayerService {
    PlayerResponseDto createPlayer(PlayerRequestDto playerDto);
    PlayerResponseDto getPlayerById(Long id);
    List<PlayerResponseDto> getAllPlayers();
    PlayerResponseDto updatePlayer(Long id, PlayerRequestDto updatedPlayerDto);
    void deletePlayer(Long id);
}
