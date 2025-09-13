package com.lld.rps.core;

import com.lld.rps.player.Player;

/**
 * Orchestrates a two-player Rock–Paper–Scissors match: collects moves, decides outcome,
 * and updates the scoreboard (composition over the two players).
 */
public class GameOrchestrator {

    private final Player player1;
    private final Player player2;
    private final Scoreboard scoreboard;

    /** Init the game with given two players, judge and new scoreboard. */
    public GameOrchestrator(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.scoreboard = new Scoreboard(player1, player2);
    }

    /**
     * Get moves from both the players and decides winner.
     *
     * @return Round with the moves and result
     */
    public Round playRound() {
        Move m1 = player1.nextMove();
        Move m2 = player2.nextMove();
        TwoPlayerResult result = TwoPlayerResult.fromMoves(m1, m2);
        scoreboard.recordScore(result);
        return new Round(player1, player2, m1, m2, result);
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    /** Wraps the Round result and sends it to the caller. */
    public record Round(Player player1,
                        Player player2,
                        Move player1Move,
                        Move player2Move,
                        TwoPlayerResult result) { }
}
