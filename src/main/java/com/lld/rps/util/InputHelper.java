package com.lld.rps.util;

import com.lld.rps.exceptions.UserInputException;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Pattern;

/** Utility class to help getting input from user with retry logic. */
public final class InputHelper {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z\\s]+$");

    private InputHelper() {
        throw new AssertionError("Util class should not be instantiated");
    }

    /** Reads input with retries until valid or max attempts exceeded. */
    public static <T> T readWithRetry(Scanner scanner, String prompt,
                                      Function<String, Optional<T>> parser, int maxAttempts) {
        int attempts = 0;
        while (attempts++ < maxAttempts) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            Optional<T> result = parser.apply(input);

            if (result.isPresent()) {
                return result.get();
            }
            System.out.println("Invalid input. Please try again.");
        }
        throw new UserInputException("Maximum attempts exceeded.");
    }

    public static int readPositiveIntWithRetry(Scanner scanner, String prompt, int maxAttempts) {
        return readWithRetry(scanner, prompt, InputHelper::parsePositiveInt, maxAttempts);
    }

    private static Optional<Integer> parsePositiveInt(String input) {
        try {
            int value = Integer.parseInt(input.trim());
            return value > 0 ? Optional.of(value) : Optional.empty();
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static String readValidatedNameWithRetry(Scanner scanner, String prompt, int retries) {
        return readWithRetry(scanner, prompt, InputHelper::validateNameInput, retries);
    }

    private static Optional<String> validateNameInput(String input) {
        if (input == null) {
            return Optional.empty();
        }

        String trimmed = input.trim();
        // Pattern check: only alphabets, spaces allowed
        if (trimmed.isEmpty() || !NAME_PATTERN.matcher(trimmed).matches()) {
            return Optional.empty();
        }
        return Optional.of(trimmed);
    }
}
