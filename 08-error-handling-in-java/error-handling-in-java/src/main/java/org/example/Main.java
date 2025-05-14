package org.example;

import java.util.List;

public class Main {

    public static double safeDivision(double a, double b) {
        if (b == 0.0) {
            return 0.0;
        }
        return a / b;
    }

    public static String getSecondLetter(String s, List<String> logMessages) {
        try {
            String secondLetter = String.valueOf(s.charAt(1));
            logMessages.add("Successfully got second letter");
            return secondLetter;
        } catch (Exception e) {
            logMessages.add("Failed to get second letter");
            return "";
        }
    }

    public static String readFile(List<String> logMessages) {
        try (FakeFileReader reader = new FakeFileReader(logMessages)) {
            return reader.read();
        } catch (Exception e) {
            logMessages.add("Error reading file");
            return "";
        }
    }

    public static double safeDivideWithCustomException(double a, double b) throws CustomDivideByZeroException {
        if (b == 0.0) {
            throw new CustomDivideByZeroException(a, b);
        }
        return a / b;
    }
}
