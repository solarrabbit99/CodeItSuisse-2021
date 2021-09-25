package com.solarrabbit.creditsuisse.problem.parasite;

import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import com.solarrabbit.creditsuisse.problem.parasite.Room.Grid;
import com.solarrabbit.creditsuisse.problem.parasite.Room.Status;

public class ParasiteX {
    private static final int[][] directions = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    private final Room room;
    private final Integer[][] energy;
    private int maxEnergy;
    private boolean isFinished;

    public ParasiteX(Room room) {
        this.room = room;
        this.energy = new Integer[room.getNumOfRows()][room.getNumOfColumns()];
        this.maxEnergy = 0;
        this.isFinished = false;
    }

    public int getTotalTick() {
        this.runSimulation();
        return this.maxEnergy;
    }

    public void runSimulation() {
        if (this.isFinished) {
            return;
        }

        Collection<Grid> firstInfected = this.room.getInfected();
        for (Grid grid : firstInfected) {
            energy[grid.getRow()][grid.getColumn()] = 0;
        }
        List<EnergyGrid> list = firstInfected.stream().map(indiv -> new EnergyGrid(indiv, 0))
                .collect(Collectors.toList());

        TreeSet<EnergyGrid> infected = new TreeSet<>(list);
        while (!infected.isEmpty()) {
            Grid grid = infected.pollFirst();
            int r = grid.getRow();
            int c = grid.getColumn();

            for (int[] dir : directions) {
                int newR = r + dir[0];
                int newC = c + dir[1];
                if (isWall(newR, newC))
                    continue;
                Status prevStatus = this.room.forceInfectPosition(newR, newC);
                if (energy[newR][newC] == null) {
                    if (prevStatus == Status.VACANT || prevStatus == Status.VACCINATED)
                        energy[newR][newC] = energy[r][c] + 1;
                    if (prevStatus == Status.HEALTHY) {
                        energy[newR][newC] = energy[r][c];
                        maxEnergy = Math.max(maxEnergy, energy[newR][newC]);
                    }
                    infected.add(new EnergyGrid(new Grid(newR, newC), energy[newR][newC]));
                }
            }
        }

        this.isFinished = true;
    }

    private class EnergyGrid extends Grid implements Comparable<EnergyGrid> {
        private int energy;

        private EnergyGrid(Grid grid, int energy) {
            super(grid.getRow(), grid.getColumn());
            this.energy = energy;
        }

        @Override
        public int compareTo(EnergyGrid o) {
            if (this.energy == o.energy) {
                if (this.getRow() != o.getRow()) {
                    return this.getRow() - o.getRow();
                } else {
                    return this.getColumn() - o.getColumn();
                }
            }
            return this.energy - o.energy;
        }

    }

    private boolean isWall(int row, int column) {
        return row < 0 || column < 0 || row >= room.getNumOfRows() || column >= room.getNumOfColumns();
    }
}
