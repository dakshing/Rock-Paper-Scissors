package com.lld.rps.core;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    @Test
    void parseAcceptsFullAndShortFormsAndTrims() {
        assertEquals(Optional.of(Move.ROCK), Move.fromInput("rock"));
        assertEquals(Optional.of(Move.PAPER), Move.fromInput("paper"));
        assertEquals(Optional.of(Move.SCISSORS), Move.fromInput("scissors"));

        assertEquals(Optional.of(Move.ROCK), Move.fromInput("r"));
        assertEquals(Optional.of(Move.PAPER), Move.fromInput("P"));
        assertEquals(Optional.of(Move.SCISSORS), Move.fromInput("s"));
    }

    @Test
    void parseRejectsInvalidAndNull() {
        assertTrue(Move.fromInput("x").isEmpty());
        assertTrue(Move.fromInput("").isEmpty());
        assertTrue(Move.fromInput(null).isEmpty());
    }

    @Test
    void beatsMatrixIsCorrect() {
        // wins
        assertTrue(Move.ROCK.beats(Move.SCISSORS));
        assertTrue(Move.PAPER.beats(Move.ROCK));
        assertTrue(Move.SCISSORS.beats(Move.PAPER));
        // losses
        assertFalse(Move.ROCK.beats(Move.PAPER));
        assertFalse(Move.PAPER.beats(Move.SCISSORS));
        assertFalse(Move.SCISSORS.beats(Move.ROCK));
        // tie
        assertFalse(Move.ROCK.beats(Move.ROCK));
    }
}
