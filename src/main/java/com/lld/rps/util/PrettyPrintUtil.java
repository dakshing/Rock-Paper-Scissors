package com.lld.rps.util;

import com.lld.rps.core.GameOrchestrator;
import com.lld.rps.core.Scoreboard;

/** Utility class to print scores and results in CLI. */
public final class PrettyPrintUtil {
    private PrettyPrintUtil() {
        throw new AssertionError("Util class should not be instantiated");
    }

    /**
     * Prints in this format.
     * player_name: ROCK | Computer: SCISSORS -> 'player_name' wins
     */
    public static void prettyPrintRound(GameOrchestrator.Round round) {
        String player1Name = round.player1().getName();
        String player2Name = round.player2().getName();

        String statusMessage = switch (round.result()) {
            case PLAYER_ONE_WINS -> "'%s' wins".formatted(player1Name);
            case PLAYER_TWO_WINS -> "'%s' wins".formatted(player2Name);
            case TIE -> "It's a tie!!";
        };
        System.out.printf("%s: %s | %s: %s -> %s%n", player1Name, round.player1Move(),
                player2Name, round.player2Move(), statusMessage);
    }

    /**
     * Prints in this format.
     * === Final Score ===
     * player_name: 2, Computer: 1, Ties: 0
     * WINNER: player_name
     */
    public static void prettyPrintFinalScore(Scoreboard scoreboard) {
        System.out.println("\n=== Final Score ===");
        System.out.println(scoreboard);

        scoreboard.overallWinner().ifPresentOrElse(
                w -> System.out.println("WINNER: " + w.getName()),
                () -> System.out.println("Result: DRAW")
        );
        System.out.println();
    }
}
