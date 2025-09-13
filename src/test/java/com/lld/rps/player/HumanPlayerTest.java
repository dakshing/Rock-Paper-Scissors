package com.lld.rps.player;

import com.lld.rps.core.Move;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class HumanPlayerTest {

    @Test
    void nextMoveSkipsInvalidInputsThenParsesValid() {
        // First two lines invalid, third is valid "r"
        String input = "junk\nwrong\nr\n";
        try (Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)))) {
            HumanPlayer hp = new HumanPlayer("You", sc);
            Move mv = hp.nextMove();
            assertEquals(Move.ROCK, mv);
        }
    }

    @Test
    void nextMoveParsesFullWordAndTrimsCase() {
        String input = "   PaPeR   \n";
        try (Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)))) {
            HumanPlayer hp = new HumanPlayer("You", sc);
            Move mv = hp.nextMove();
            assertEquals(Move.PAPER, mv);
        }
    }

    @Test
    void nextMoveThrowsOnEofNoInputAvailable() {
        // EOF immediately — HumanPlayer should throw a RuntimeException (e.g., IllegalStateException)
        try (Scanner sc = new Scanner(new ByteArrayInputStream(new byte[0]))) {
            HumanPlayer hp = new HumanPlayer("You", sc);
            assertThrows(RuntimeException.class, hp::nextMove);
        }
    }
}
