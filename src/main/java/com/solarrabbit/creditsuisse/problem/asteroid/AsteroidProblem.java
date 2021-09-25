package com.solarrabbit.creditsuisse.problem.asteroid;

import com.solarrabbit.creditsuisse.Solvable;
import org.json.JSONObject;

public class AsteroidProblem implements Solvable {
    private final String original;
    private double highestScore;
    private int latestCenter;
    private char currentChar;

    public AsteroidProblem(String original) {
        this.original = original;
        this.highestScore = 1;
        this.latestCenter = 0;
    }

    @Override
    public JSONObject solve() {
        for (int i = 0; i < original.length(); i++) {
            double score = calculateScore(i);
            if (score > highestScore) {
                latestCenter = i;
                highestScore = score;
            }
        }

        JSONObject answer = new JSONObject();
        answer.put("input", original);
        answer.put("score", highestScore);
        answer.put("origin", latestCenter);
        return answer;
    }

    private double calculateScore(int index) {
        this.currentChar = original.charAt(index);
        StringBuilder builder = new StringBuilder(original.substring(0, index));
        String leftWave = builder.reverse().toString();
        String rightWave = original.substring(index + 1);

        return calculateRecursively(leftWave, rightWave, 0);
    }

    private double calculateRecursively(String leftWave, String rightWave, double accumulativeScore) {
        if (leftWave.isEmpty() || rightWave.isEmpty())
            return accumulativeScore == 0 ? 1 : accumulativeScore;

        char c = leftWave.charAt(0);
        if (!rightWave.startsWith(String.valueOf(c)))
            return accumulativeScore == 0 ? 1 : accumulativeScore;

        int countLeft = countPrefixes(c, leftWave);
        int countRight = countPrefixes(c, rightWave);
        int count = countLeft + countRight;
        String trimmedLeft = leftWave.substring(countLeft);
        String trimmedRight = rightWave.substring(countRight);

        if (c == this.currentChar && accumulativeScore == 0) {
            count++;
            return calculateRecursively(trimmedLeft, trimmedRight, accumulativeScore + count * getMultiplier(count));
        }
        return calculateRecursively(trimmedLeft, trimmedRight, accumulativeScore + count * getMultiplier(count))
                + (accumulativeScore == 0 ? 1 : 0);
    }

    private int countPrefixes(char c, String str) {
        int count = 0;
        int pointer = 0;
        while (pointer < str.length() && str.charAt(pointer) == c) {
            count++;
            pointer++;
        }
        return count;
    }

    private double getMultiplier(int occurences) {
        if (occurences >= 10)
            return 2;
        if (occurences > 7)
            return 1.5;
        return 1;
    }

}
