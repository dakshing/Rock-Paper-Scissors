package com.lld.rps.core;

import com.lld.rps.player.Player;
import java.util.Optional;

/** Scoreboard for two players that tracks number of wins and ties. */
public class Scoreboard {
    private final Player player1;
    private final Player player2;
    private int p1Wins;
    private int p2Wins;
    private int ties;

    public Scoreboard(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    /** Takes in result and updates the scoreboard. */
    public void recordScore(TwoPlayerResult result) {
        switch (result) {
            case PLAYER_ONE_WINS -> p1Wins++;
            case PLAYER_TWO_WINS -> p2Wins++;
            case TIE -> ties++;
            default -> throw new IllegalArgumentException("Unexpected result passed");
        }
    }

    /**
     * Logic to decide the overall winner based on the previous rounds.
     *
     * @return Winner, null in case of tie
     */
    public Optional<Player> overallWinner() {
        if (p1Wins == p2Wins) {
            return Optional.empty();
        }
        return Optional.of(p1Wins > p2Wins ? player1 : player2);
    }

    @Override
    public String toString() {
        return "%s: %d, %s: %d, Ties: %d"
                .formatted(player1.getName(), p1Wins, player2.getName(), p2Wins, ties);
    }

    public int getPlayer1Wins() {
        return p1Wins;
    }

    public int getPlayer2Wins() {
        return p2Wins;
    }

    public int getTies() {
        return ties;
    }
}
