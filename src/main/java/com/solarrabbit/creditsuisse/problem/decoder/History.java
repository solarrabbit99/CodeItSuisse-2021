package com.solarrabbit.creditsuisse.problem.decoder;

import java.util.ArrayList;
import java.util.List;

public class History {
    private final List<String> outputReceived;
    private final int result;

    public History(List<String> output_received, int result) {
        this.outputReceived = output_received;
        this.result = result;
    }

    public int getRightPositions() {
        return result - this.getWrongPositions() * 10;
    }

    public int getWrongPositions() {
        return result / 10;
    }

    public boolean isConsistent(String[] expected) {
        int correctPositions = 0;
        List<String> testStrings = new ArrayList<>(outputReceived);
        for (int i = expected.length - 1; i >= 0; i--) {
            if (expected[i].equals(testStrings.get(i))) {
                testStrings.remove(i);
                expected[i] = null;
                correctPositions++;
            }
        }

        if (correctPositions != getRightPositions())
            return false;

        int wrongPositions = 0;
        for (int i = 0; i < expected.length; i++) {
            if (expected[i] != null && testStrings.remove(expected[i])) {
                wrongPositions++;
            }
        }

        return wrongPositions == getWrongPositions();
    }
}