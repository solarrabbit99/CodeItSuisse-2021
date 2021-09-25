package com.solarrabbit.creditsuisse.stockhunter;

import java.util.TreeSet;
import com.solarrabbit.creditsuisse.Solvable;
import org.json.JSONArray;
import org.json.JSONObject;

public class StockHunterProblem implements Solvable {
    protected final Grid entryPoint;
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
        object.put("minimumCost", minCost());
        return object;
    }

    private int minCost() {
        int length = 2 * Math.max(targetPoint.getX() + 1, targetPoint.getY() + 1);
        int[][] riskCosts = calculateRiskIntCosts(length, length);

        int[][] weights = initInf(length, length);
        weights[0][0] = 0;
        boolean[][] visited = new boolean[length][length];
        visited[0][0] = true;
        TreeSet<RankedGrid> riskLevels = new TreeSet<>();
        riskLevels.add(new RankedGrid(new Grid(0, 0), 0));
        final int[][] directions = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        while (!riskLevels.isEmpty()) {
            RankedGrid grid = riskLevels.pollFirst();
            if (grid.equals(targetPoint))
                return grid.level;

            for (int[] dir : directions) {
                int newR = grid.first + dir[0];
                int newC = grid.second + dir[1];
                Grid newGrid = new Grid(newR, newC);
                if (!isWall(newGrid, length, length) && !visited[newR][newC]
                        && weights[newR][newC] > weights[grid.first][grid.second] + riskCosts[newR][newC]) {
                    weights[newR][newC] = weights[grid.first][grid.second] + riskCosts[newR][newC];
                    riskLevels.add(new RankedGrid(newGrid, weights[newR][newC]));
                }
            }

            visited[grid.first][grid.second] = true;
        }

        return 0;
    }

    private boolean isWall(Grid grid, int row, int col) {
        return grid.first < 0 || grid.second < 0 || grid.first >= row || grid.second >= col;
    }

    private int[][] initInf(int row, int col) {
        int[][] result = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result[i][j] = Integer.MAX_VALUE;
            }
        }
        return result;
    }

    private int[][] calculateRiskLevels(int row, int col) {
        int[][] riskLevels = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int riskIndex = 0;
                if (i == 0 && j == 0) {
                    riskIndex = 0;
                } else if (i == 0) {
                    riskIndex = j * horizontalStepper;
                } else if (j == 0) {
                    riskIndex = i * verticalStepper;
                } else {
                    riskIndex = riskLevels[i - 1][j] * riskLevels[i][j - 1];
                }
                riskLevels[i][j] = (riskIndex + gridDepth) % gridKey;
            }
        }
        return riskLevels;
    }

    private int[][] calculateRiskIntCosts(int row, int col) {
        char[][] costs = calculateRiskCosts(row, col);
        int[][] result = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                switch (costs[i][j]) {
                    case 'L':
                        result[i][j] = 3;
                        break;
                    case 'M':
                        result[i][j] = 2;
                        break;
                    case 'S':
                        result[i][j] = 1;
                        break;
                    default:
                        break;
                }
            }
        }
        return result;
    }

    public char[][] calculateRiskCosts() {
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

    public static class Grid {
        protected final int first;
        protected final int second;

        public Grid(int first, int second) {
            this.first = first;
            this.second = second;
        }

        private int getX() {
            return this.first;
        }

        private int getY() {
            return this.second;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Grid))
                return false;
            Grid grid = (Grid) obj;
            return this.first == grid.first && this.second == grid.second;
        }
    }

}
