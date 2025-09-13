package com.lld.rps.core;

/** A result enum for two-player games. */
public enum TwoPlayerResult {
    PLAYER_ONE_WINS, PLAYER_TWO_WINS, TIE;

    /**
     * Return the Result corresponding to the moves made by the players.
     *
     * @param m1 First player's move
     * @param m2 Second player's move
     * @return result
     */
    public static TwoPlayerResult fromMoves(Move m1, Move m2) {
        if (m1 == m2) {
            return TwoPlayerResult.TIE;
        }
        return m1.beats(m2) ? TwoPlayerResult.PLAYER_ONE_WINS : TwoPlayerResult.PLAYER_TWO_WINS;
    }
}
