package com.example.fifantinyo.controller;

import com.example.fifantinyo.dto.MatchRequestDto;
import com.example.fifantinyo.dto.MatchResponseDto;
import com.example.fifantinyo.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @GetMapping
    public List<MatchResponseDto> getAllMatches() {
        return matchService.getAllMatches();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchResponseDto> getMatchById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(matchService.getMatchById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/player/{playerId}")
    public List<MatchResponseDto> getAllMatchesByPlayer(@PathVariable Long playerId) {
        return matchService.getAllMatchesByPlayer(playerId);
    }

    @GetMapping("/range")
    public List<MatchResponseDto> getMatchesByDateRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) {
        return matchService.getMatchesByDateRange(start, end);
    }

    @PostMapping
    public MatchResponseDto createMatch(@RequestBody MatchRequestDto dto) {
        return matchService.createMatch(dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        try {
            matchService.deleteMatch(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
