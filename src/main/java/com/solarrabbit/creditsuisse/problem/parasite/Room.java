package com.solarrabbit.creditsuisse.problem.parasite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Room implements Cloneable {
    private final List<List<Integer>> array;
    private final int row;
    private final int column;

    public Room(List<List<Integer>> array) {
        this.array = array;
        this.row = array.size();
        this.column = array.get(0).size();
    }

    @Override
    protected Object clone() {
        ArrayList<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < this.row; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < this.column; j++) {
                row.add(this.array.get(i).get(j));
            }
            list.add(row);
        }
        return new Room(list);
    }

    public Status getStatus(int row, int column) {
        int id = this.array.get(row).get(column);
        return Status.valueOf(id);
    }

    public Status forceInfectPosition(int row, int column) {
        Status status = getStatus(row, column);
        this.array.get(row).set(column, Status.INFECTED.id);
        return status;
    }

    public boolean infectPosition(int row, int column) {
        Status status = getStatus(row, column);
        if (status == Status.HEALTHY) {
            this.array.get(row).set(column, Status.INFECTED.id);
            return true;
        }
        return false;
    }

    public Collection<Grid> getInfected() {
        List<Grid> list = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (getStatus(i, j) == Status.INFECTED) {
                    Grid infected = new Grid(i, j);
                    list.add(infected);
                }
            }
        }
        return list;
    }

    public int getNumOfRows() {
        return this.row;
    }

    public int getNumOfColumns() {
        return this.column;
    }

    public static class Grid {
        private final int row;
        private final int column;

        public Grid(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return this.row;
        }

        public int getColumn() {
            return this.column;
        }
    }

    public static enum Status {
        VACANT(0), HEALTHY(1), VACCINATED(2), INFECTED(3);

        private final int id;

        private Status(int id) {
            this.id = id;
        }

        private static Status valueOf(int id) {
            for (Status status : Status.values()) {
                if (status.id == id) {
                    return status;
                }
            }
            return null;
        }
    }

}
