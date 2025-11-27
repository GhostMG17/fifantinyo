package com.example.fifantinyo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "matches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Match {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "player1_id")
    private Player player1;

    @ManyToOne(optional = false)
    @JoinColumn(name = "player2_id")
    private Player player2;

    @Column(name = "score1", nullable = false)
    private int score1;

    @Column(name = "score2", nullable = false)
    private int score2;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "match_day", nullable = false)
    private int matchDay; // 1 = Пн, ..., 5 = Пт

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
        // Автоматический подсчёт дня недели
        DayOfWeek dow = date.getDayOfWeek();
        if (dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY) {
            throw new IllegalArgumentException("Матчи разрешены только с понедельника по пятницу!");
        }
        matchDay = dow.getValue(); // 1 = Пн ... 5 = Пт
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
