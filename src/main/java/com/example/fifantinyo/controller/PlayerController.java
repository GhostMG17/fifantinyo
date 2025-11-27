package com.example.fifantinyo.controller;

import com.example.fifantinyo.dto.PlayerRequestDto;
import com.example.fifantinyo.dto.PlayerResponseDto;
import com.example.fifantinyo.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    public List<PlayerResponseDto> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponseDto> getPlayerById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(playerService.getPlayerById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public PlayerResponseDto createPlayer(@RequestBody PlayerRequestDto dto) {
        return playerService.createPlayer(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerResponseDto> updatePlayer(@PathVariable Long id, @RequestBody PlayerRequestDto dto) {
        try {
            return ResponseEntity.ok(playerService.updatePlayer(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        try {
            playerService.deletePlayer(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
