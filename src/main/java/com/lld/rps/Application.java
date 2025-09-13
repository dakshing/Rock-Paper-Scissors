package com.lld.rps;

import com.lld.rps.core.GameOrchestrator;
import com.lld.rps.exceptions.UserInputException;
import com.lld.rps.player.ComputerPlayer;
import com.lld.rps.player.HumanPlayer;
import com.lld.rps.player.Player;
import com.lld.rps.util.InputHelper;
import com.lld.rps.util.PrettyPrintUtil;
import java.util.Scanner;

/** Paper-Rock-Scissors application main class. */
public class Application {
    /** Starts the console application and plays a user-configured number of rounds. */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== Paper-Rock-Scissors ===");
            int rounds = InputHelper.readPositiveIntWithRetry(scanner,
                    "How many rounds do you want to play? ", 5);
            String name = InputHelper.readValidatedNameWithRetry(scanner, "Enter name: ", 5);

            Player player1 = new HumanPlayer(name, scanner);
            Player player2 = new ComputerPlayer();
            GameOrchestrator gameOrchestrator = new GameOrchestrator(player1, player2);

            // Play n rounds
            for (int i = 1; i <= rounds; i++) {
                System.out.printf("%nRound %d of %d%n", i, rounds);
                GameOrchestrator.Round round = gameOrchestrator.playRound();
                PrettyPrintUtil.prettyPrintRound(round);
            }

            PrettyPrintUtil.prettyPrintFinalScore(gameOrchestrator.getScoreboard());
        } catch (UserInputException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.err.printf("Unexpected error occurred: %s.%n", e.getMessage());
        } finally {
            System.out.println("Ending game. Thanks for playing!");
        }
    }
}
