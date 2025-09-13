package com.lld.rps.player;

import com.lld.rps.core.Move;
import java.util.Random;

/** A Computer/Bot which uses a Random generator to simulate a player. */
public class ComputerPlayer implements Player {

    private final Random randomGenerator;

    /** Default constructor that initializes with java random. */
    public ComputerPlayer() {
        this(new Random());
    }

    public ComputerPlayer(Random random) {
        this.randomGenerator = random;
    }

    @Override
    public String getName() {
        return "Computer";
    }

    @Override
    public Move nextMove() {
        int index = randomGenerator.nextInt(Move.values().length);
        return Move.values()[index];
    }
}
