package com.solarrabbit.creditsuisse.problem.stockhunter;

import com.solarrabbit.creditsuisse.problem.stockhunter.StockHunterProblem.Grid;

import org.junit.Test;

public class StockHunterTest {
    @Test
    public void test() {
        StockHunterProblem problem = new StockHunterProblem(new Grid(0, 0), new Grid(2, 2), 156, 20183, 16807, 48271);
        char[][] pattern = problem.calculateRiskCosts();
        System.out.println(pattern[0][1] + "");
    }
}
