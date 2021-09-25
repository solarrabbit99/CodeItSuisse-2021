package com.solarrabbit.creditsuisse.problem.parasite;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import com.solarrabbit.creditsuisse.problem.parasite.Room.Grid;
import com.solarrabbit.creditsuisse.problem.parasite.Room.Status;

public class ParasiteA {
    private static final int[][] directions = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    private final Room room;
    private final Integer[][] infectedTick;
    private boolean isFinished;

    public ParasiteA(Room room) {
        this.room = room;
        this.infectedTick = new Integer[room.getNumOfRows()][room.getNumOfColumns()];
        this.isFinished = false;
    }

    public int solve(int row, int column) {
        this.runSimulation();
        return infectedTick[row][column] == null ? -1 : infectedTick[row][column];
    }

    public int getTotalTick() {
        this.runSimulation();
        int maxTicks = 0;
        for (int i = 0; i < room.getNumOfRows(); i++) {
            for (int j = 0; j < room.getNumOfColumns(); j++) {
                Status status = room.getStatus(i, j);
                if (status == Status.HEALTHY) {
                    return -1;
                }
                if (status == Status.INFECTED) {
                    maxTicks = Math.max(maxTicks, infectedTick[i][j]);
                }
            }
        }

        return maxTicks;
    }

    public void runSimulation() {
        if (this.isFinished) {
            return;
        }

        Collection<Grid> firstInfected = this.room.getInfected();
        for (Grid grid : firstInfected) {
            infectedTick[grid.getRow()][grid.getColumn()] = 0;
        }

        Queue<Grid> infected = new LinkedList<>(this.room.getInfected());
        while (!infected.isEmpty()) {
            Grid grid = infected.poll();
            int r = grid.getRow();
            int c = grid.getColumn();

            for (int[] dir : directions) {
                int newR = r + dir[0];
                int newC = c + dir[1];
                if (!isWall(newR, newC) && infectedTick[newR][newC] == null && this.room.infectPosition(newR, newC)) {
                    infectedTick[newR][newC] = infectedTick[r][c] + 1;
                    infected.add(new Grid(newR, newC));
                }
            }
        }

        this.isFinished = true;
    }

    public boolean isWall(int row, int column) {
        return row < 0 || column < 0 || row >= room.getNumOfRows() || column >= room.getNumOfColumns();
    }
}
