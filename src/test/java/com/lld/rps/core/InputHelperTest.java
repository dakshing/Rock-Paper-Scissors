package com.lld.rps.core;

import com.lld.rps.exceptions.UserInputException;
import com.lld.rps.util.InputHelper;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class InputHelperTest {

    @Test
    void readPositiveIntWithRetryEventuallyParses() {
        // two invalids then a valid "3"
        String input = "abc\n-1\n3\n";
        try (Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)))) {
            int n = InputHelper.readPositiveIntWithRetry(sc, "Test Prompt: ", 5);
            assertEquals(3, n);
        }
    }

    @Test
    void readWithRetryThrowsAfterMaxAttempts() {
        // three invalids, maxAttempts=3
        String input = "x\ny\nz\n";
        try (Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)))) {
            assertThrows(UserInputException.class, () ->
                    InputHelper.readWithRetry(sc, "Test Prompt: ",
                            s -> Optional.empty(), 3)
            );
        }
    }

    @Test
    void readPositiveIntWithRetryInvalidValues() {
        String input = "abc\n-5\n0\n";
        try (Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)))) {
            assertThrows(UserInputException.class, () ->
                    InputHelper.readPositiveIntWithRetry(sc, "Test Prompt: ", 3)
            );
        }
    }
}
