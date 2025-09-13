package com.lld.rps.core;

import com.lld.rps.player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameOrchestratorTest {

    static class ScriptedPlayer implements Player {
        private final String name;
        private final Move[] script;
        private int i = 0;
        ScriptedPlayer(String name, Move... script) {
            this.name = name; this.script = script;
        }
        @Override public String getName() { return name; }
        @Override public Move nextMove() { return script[i++ % script.length]; }
    }

    static class ThrowingPlayer implements Player {
        private final String name;
        ThrowingPlayer(String name) { this.name = name; }
        @Override public String getName() { return name; }
        @Override public Move nextMove() { throw new RuntimeException("error!!"); }
    }

    static class NullMovePlayer implements Player {
        private final String name;
        NullMovePlayer(String name) { this.name = name; }
        @Override public String getName() { return name; }
        @Override public Move nextMove() { return null; }
    }

    static class OKPlayer implements Player {
        private final String name;
        private final Move move;
        OKPlayer(String name, Move move) { this.name = name; this.move = move; }
        @Override public String getName() { return name; }
        @Override public Move nextMove() { return move; }
    }

    @Test
    void playRoundUpdatesScoreboardOnWin() {
        var p1 = new ScriptedPlayer("P1", Move.PAPER);
        var p2 = new ScriptedPlayer("P2", Move.ROCK);

        var orchestrator = new GameOrchestrator(p1, p2);
        var round = orchestrator.playRound();

        assertEquals(TwoPlayerResult.PLAYER_ONE_WINS, round.result());
        assertEquals(1, orchestrator.getScoreboard().getPlayer1Wins());
        assertEquals(0, orchestrator.getScoreboard().getPlayer2Wins());
        assertEquals(0, orchestrator.getScoreboard().getTies());
    }

    @Test
    void playRoundRecordsTie() {
        var p1 = new ScriptedPlayer("P1", Move.SCISSORS);
        var p2 = new ScriptedPlayer("P2", Move.SCISSORS);

        var orchestrator = new GameOrchestrator(p1, p2);
        var round = orchestrator.playRound();

        assertEquals(TwoPlayerResult.TIE, round.result());
        assertEquals(1, orchestrator.getScoreboard().getTies());
    }

    @Test
    void misbehavingPlayerNextMoveBubblesAsRuntimeException() {
        var p1 = new ThrowingPlayer("Bad");
        var p2 = new OKPlayer("Good", Move.ROCK);

        var orchestrator = new GameOrchestrator(p1, p2);
        assertThrows(RuntimeException.class, orchestrator::playRound);
    }

    @Test
    void nullMoveFromPlayerCausesFailureInDecide() {
        var p1 = new NullMovePlayer("Nuller");
        var p2 = new OKPlayer("Good", Move.ROCK);

        var orchestrator = new GameOrchestrator(p1, p2);
        assertThrows(NullPointerException.class, orchestrator::playRound);
    }
}
