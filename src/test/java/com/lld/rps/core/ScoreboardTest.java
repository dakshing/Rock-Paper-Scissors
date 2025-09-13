package com.lld.rps.core;

import com.lld.rps.player.Player;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScoreboardTest {

    static final class StubPlayer implements Player {
        private final String name;

        StubPlayer(String name) {
            this.name = name;
        }

        @Override
            public Move nextMove() {
                return Move.ROCK;
            }

        @Override
        public String getName() {
            return name;
        }

    }

    @Test
    void recordsWinsAndTiesAndReportsDraw() {
        var p1 = new StubPlayer("P1");
        var p2 = new StubPlayer("P2");
        var sb = new Scoreboard(p1, p2);

        sb.recordScore(TwoPlayerResult.PLAYER_ONE_WINS);
        sb.recordScore(TwoPlayerResult.PLAYER_TWO_WINS);
        sb.recordScore(TwoPlayerResult.TIE);

        assertEquals(Optional.empty(), sb.overallWinner());
    }

    @Test
    void reportsOverallWinnerWhenUnequal() {
        var p1 = new StubPlayer("P1");
        var p2 = new StubPlayer("P2");
        var sb = new Scoreboard(p1, p2);

        sb.recordScore(TwoPlayerResult.PLAYER_ONE_WINS);
        sb.recordScore(TwoPlayerResult.PLAYER_ONE_WINS);
        sb.recordScore(TwoPlayerResult.PLAYER_TWO_WINS);

        var winner = sb.overallWinner();
        assertTrue(winner.isPresent());
        assertEquals("P1", winner.get().getName());
    }
}
