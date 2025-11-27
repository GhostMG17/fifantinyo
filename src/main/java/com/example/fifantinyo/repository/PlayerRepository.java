package com.example.fifantinyo.repository;


import com.example.fifantinyo.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    boolean existsByName(String name);
}
