package com.example.fifantinyo.service.impl;

import com.example.fifantinyo.dto.MatchRequestDto;
import com.example.fifantinyo.dto.MatchResponseDto;
import com.example.fifantinyo.dto.PlayerStatsDto;
import com.example.fifantinyo.enums.Period;
import com.example.fifantinyo.mapper.MatchMapper;
import com.example.fifantinyo.model.Match;
import com.example.fifantinyo.model.Player;
import com.example.fifantinyo.repository.MatchRepository;
import com.example.fifantinyo.repository.PlayerRepository;
import com.example.fifantinyo.service.MatchService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;


@Service
@Transactional
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;

    public MatchServiceImpl(MatchRepository matchRepository, PlayerRepository playerRepository) {
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
    }



    @Override
    public MatchResponseDto createMatch(MatchRequestDto matchDto) {
        Player p1 = playerRepository.findById(matchDto.getPlayer1Id())
                .orElseThrow(() -> new RuntimeException("Player 1 not found"));

        Player p2 = playerRepository.findById(matchDto.getPlayer2Id())
                .orElseThrow(() -> new RuntimeException("Player 2 not found"));

        Match match = Match.builder()
                .player1(p1)
                .player2(p2)
                .score1(matchDto.getScorePlayer1())
                .score2(matchDto.getScorePlayer2())
                .date(LocalDate.parse(matchDto.getDate()))
                .build();

        matchRepository.save(match);

        return MatchMapper.toDto(match);
    }

    @Override
    public MatchResponseDto getMatchById(Long id) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found"));
        return MatchMapper.toDto(match);
    }


    @Override
    public List<MatchResponseDto> getAllMatches() {
        return matchRepository.findAll()
                .stream()
                .map(MatchMapper::toDto)
                .toList();
    }


    @Override
    public List<MatchResponseDto> getAllMatchesByPlayer(Long playerId) {

        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        return matchRepository.findByPlayer1OrPlayer2(player, player)
                .stream()
                .map(MatchMapper::toDto)
                .toList();
    }


    @Override
    public List<MatchResponseDto> getMatchesByDateRange(LocalDate start, LocalDate end) {
        return matchRepository.findByDateBetween(start, end)
                .stream()
                .map(MatchMapper::toDto)
                .toList();
    }

    @Override
    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }


    @Override
    public List<PlayerStatsDto> getPlayerStatsByPeriod(Period period){
        LocalDate now = LocalDate.now();
        LocalDate start;

        switch (period){
            case DAY -> start = now;
            case WEEK -> start = now.with(DayOfWeek.MONDAY);
            case MONTH -> start = now.withDayOfMonth(1);
            case YEAR -> start = now.withDayOfYear(1);
            default -> start = now;
        }

        return calculateStats(start,now);
    }

    @Override
    public PlayerStatsDto getChampion(Period period) {
        return getPlayerStatsByPeriod(period).stream()
                .max((a, b) -> Integer.compare(a.getTotalWins(), b.getTotalWins()))
                .orElse(null);
    }

    @Override
    public PlayerStatsDto getLoser(Period period) {
        return getPlayerStatsByPeriod(period).stream()
                .max((a, b) -> Integer.compare(a.getTotalLosses(), b.getTotalLosses()))
                .orElse(null);
    }

    private List<PlayerStatsDto> calculateStats(LocalDate start, LocalDate end) {
        List<Player> players = playerRepository.findAll();
        List<Match> matches = matchRepository.findByDateBetween(start, end);

        return players.stream().map(player -> {
            int goals = 0, wins = 0, losses = 0, draws = 0;

            for (Match match : matches) {
                DayOfWeek day = match.getDate().getDayOfWeek();
                if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) continue;

                boolean isPlayer1 = match.getPlayer1().getId().equals(player.getId());
                boolean isPlayer2 = match.getPlayer2().getId().equals(player.getId());
                if (!isPlayer1 && !isPlayer2) continue;

                int playerGoals = isPlayer1 ? match.getScore1() : match.getScore2();
                int opponentGoals = isPlayer1 ? match.getScore2() : match.getScore1();

                goals += playerGoals;
                if (playerGoals > opponentGoals) wins++;
                else if (playerGoals < opponentGoals) losses++;
                else draws++;
            }

            return PlayerStatsDto.builder()
                    .playerId(player.getId())
                    .playerName(player.getName())
                    .totalGoals(goals)
                    .totalWins(wins)
                    .totalLosses(losses)
                    .totalDraws(draws)
                    .build();
        }).toList();
    }

}

