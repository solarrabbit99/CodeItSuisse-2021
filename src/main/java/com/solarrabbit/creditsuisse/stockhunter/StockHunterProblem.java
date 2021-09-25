package com.solarrabbit.creditsuisse.stockhunter;

import java.util.HashMap;
import java.util.Map;
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
        Map<Grid, Integer> riskLevels = new HashMap<>();
        return 0;
    }

    private int[][] calculateRiskLevels() {
        int[][] riskLevels = new int[targetPoint.getX() + 1][targetPoint.getY() + 1];
        for (int i = 0; i <= targetPoint.getX(); i++) {
            for (int j = 0; j <= targetPoint.getY(); j++) {
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
        int[][] riskLevels = calculateRiskLevels();
        char[][] riskCosts = new char[targetPoint.getX() + 1][targetPoint.getY() + 1];
        for (int i = 0; i <= targetPoint.getX(); i++) {
            for (int j = 0; j <= targetPoint.getY(); j++) {
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

    // private static class RankedGrid extends Grid {

    // }

    private static class Grid {
        private final int first;
        private final int second;

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
