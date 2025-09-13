package com.lld.rps.player;

import com.lld.rps.core.Move;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Covers the basic behavior of ComputerPlayer.
 * This version does not assume a seedable constructor — it just exercises the method.
 */
class ComputerPlayerTest {

    @Test
    void nextMoveReturnsAValidEnumConstant() {
        ComputerPlayer cp = new ComputerPlayer();
        Move mv = cp.nextMove();
        assertNotNull(mv);
        assertTrue(EnumSet.allOf(Move.class).contains(mv),
                "Computer move must be one of ROCK/PAPER/SCISSORS");
    }

    @Test
    void sameSeedProducesIdenticalSequence() {
        ComputerPlayer a = new ComputerPlayer(new Random(12345));
        ComputerPlayer b = new ComputerPlayer(new Random(12345));

        // With the same seed, the sequence of moves should be identical
        for (int i = 0; i < 50; i++) {
            assertEquals(a.nextMove(), b.nextMove(), "Sequences should match with same seed");
        }
    }
}
