package com.solarrabbit.creditsuisse.stockhunter;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import com.solarrabbit.creditsuisse.Solvable;
import org.json.JSONArray;
import org.json.JSONObject;

public class StockHunterProblem implements Solvable {
    private final Grid entryPoint;
    private final Grid targetPoint;
    private final int gridDepth;
    private final int gridKey;
    private final int horizontalStepper;
    private final int verticalStepper;

    public StockHunterProblem(Grid entryPoint, Grid targetPoint, int gridDepth, int gridKey, int horizontalStepper,
            int verticalStepper) {
        this.entryPoint = entryPoint;
        this.targetPoint = targetPoint;
        this.gridDepth = gridDepth;
        this.gridKey = gridKey;
        this.horizontalStepper = horizontalStepper;
        this.verticalStepper = verticalStepper;
    }

    @Override
    public JSONObject solve() {
        JSONArray array = new JSONArray(calculateRiskCosts());
        JSONObject object = new JSONObject();
        object.put("gridMap", array);
        return object;
    }

    private int minCost() {
        int length = 2 * Math.max(targetPoint.getX() + 1, targetPoint.getY() + 1);
        char[][] riskCosts = calculateRiskCosts(length, length);
        TreeSet<RankedGrid> riskLevels = new TreeSet<>();
        riskLevels.add(new RankedGrid(new Grid(0, 0), 0));

        while (!riskLevels.isEmpty()) {

        }

        return 0;
    }

    private int[][] calculateRiskLevels(int row, int col) {
        int[][] riskLevels = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int riskIndex = 0;
                if (i == 0 && j == 0) {
                    riskIndex = 0;
                } else if (j == 0) {
                    riskIndex = i * horizontalStepper;
                } else if (i == 0) {
                    riskIndex = j * verticalStepper;
                } else {
                    riskIndex = riskLevels[i - 1][j] * riskLevels[i][j - 1];
                }
                riskLevels[i][j] = (riskIndex + gridDepth) % gridKey;
            }
        }
        return riskLevels;
    }

    private char[][] calculateRiskCosts() {
        return calculateRiskCosts(targetPoint.getX() + 1, targetPoint.getY() + 1);
    }

    private char[][] calculateRiskCosts(int row, int col) {
        int[][] riskLevels = calculateRiskLevels(row, col);
        char[][] riskCosts = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int remainder = riskLevels[i][j] % 3;
                switch (remainder) {
                    case 0:
                        riskCosts[i][j] = 'L';
                        break;
                    case 1:
                        riskCosts[i][j] = 'M';
                        break;
                    case 2:
                        riskCosts[i][j] = 'S';
                        break;
                    default:
                        break;
                }
            }
        }
        return riskCosts;
    }

    private static class RankedGrid extends Grid implements Comparable<RankedGrid> {
        private int level;

        private RankedGrid(Grid grid, int level) {
            super(grid.getX(), grid.getY());
            this.level = level;
        }

        @Override
        public int compareTo(RankedGrid o) {
            if (this.level == o.level) {
                if (this.first != o.first) {
                    return this.first - o.first;
                } else {
                    return this.second - o.second;
                }
            }
            return this.level - o.level;
        }
    }

    private static class Grid {
        protected final int first;
        protected final int second;

        private Grid(int first, int second) {
            this.first = first;
            this.second = second;
        }

        private int getX() {
            return this.first;
        }

        private int getY() {
            return this.second;
        }
    }

}
