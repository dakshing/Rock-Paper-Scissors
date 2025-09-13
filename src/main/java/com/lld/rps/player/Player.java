package com.lld.rps.player;

import com.lld.rps.core.Move;

/** Player strategy to make it extensible and easy to add other types of players later. */
public interface Player {
    String getName();

    Move nextMove();
}
