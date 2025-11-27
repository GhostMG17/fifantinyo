package com.example.fifantinyo.repository;

import com.example.fifantinyo.model.Match;
import com.example.fifantinyo.model.Player;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByPlayer1OrPlayer2(Player player1, Player player2);
    List<Match> findByDateBetween(LocalDate start, LocalDate end);
}
