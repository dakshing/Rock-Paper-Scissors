package com.lld.rps.player;

import com.lld.rps.core.Move;
import com.lld.rps.util.InputHelper;
import java.util.Scanner;

/** A Player which takes input from CLI. */
public class HumanPlayer implements Player {

    private final String name;
    private final Scanner scanner;

    public HumanPlayer(String name, Scanner scanner) {
        this.name = name;
        this.scanner = scanner;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Move nextMove() {
        String prompt = String.format("%s enter your move (rock, paper, scissors): ", name);
        return InputHelper.readWithRetry(scanner, prompt, Move::fromInput, 5);
    }
}
