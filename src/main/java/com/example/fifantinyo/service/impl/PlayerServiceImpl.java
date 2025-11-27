package com.example.fifantinyo.service.impl;

import com.example.fifantinyo.dto.PlayerRequestDto;
import com.example.fifantinyo.dto.PlayerResponseDto;
import com.example.fifantinyo.mapper.PlayerMapper;
import com.example.fifantinyo.model.Player;
import com.example.fifantinyo.repository.PlayerRepository;
import com.example.fifantinyo.service.PlayerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    @Override
    public PlayerResponseDto createPlayer(PlayerRequestDto playerDto){
        Player player = Player.builder()
                .name(playerDto.getName())
                .build();

        playerRepository.save(player);
        return PlayerMapper.toDto(player);
    }

    @Override
    public PlayerResponseDto getPlayerById(Long id){
        Player player = playerRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Player not found"));
        return PlayerMapper.toDto(player);
    }

    @Override
    public List<PlayerResponseDto> getAllPlayers(){
        return playerRepository.findAll()
                .stream()
                .map(PlayerMapper::toDto)
                .toList();
    }

    @Override
    public PlayerResponseDto updatePlayer(Long id, PlayerRequestDto updatedPlayerDto){
        Player player = playerRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Player not found"));

        player.setName(updatedPlayerDto.getName());

        playerRepository.save(player);

        return PlayerMapper.toDto(player);
    }

    @Override
    public void deletePlayer(Long id){
        playerRepository.deleteById(id);
    }

}
