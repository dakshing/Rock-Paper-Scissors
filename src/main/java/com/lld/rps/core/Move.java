package com.lld.rps.core;

import java.util.Optional;

/** Enum strategy with an enum for each move and encapsulates "who beats who" logic. */
public enum Move {
    PAPER {
        @Override
        public boolean beats(Move other) {
            return other == ROCK;
        }
    },
    ROCK {
        @Override
        public boolean beats(Move other) {
            return other == SCISSORS;
        }
    },
    SCISSORS {
        @Override
        public boolean beats(Move other) {
            return other == PAPER;
        }
    };

    public abstract boolean beats(Move other);

    /**
     * A factory method that matches the input to the corresponding enum.
     *
     * @return Move, else empty if the input is invalid
     */
    public static Optional<Move> fromInput(String input) {
        if (input == null) {
            return Optional.empty();
        }
        return switch (input.toLowerCase()) {
            case "r", "rock" -> Optional.of(ROCK);
            case "p", "paper" -> Optional.of(PAPER);
            case "s", "scissors" -> Optional.of(SCISSORS);
            default -> Optional.empty();
        };
    }
}
