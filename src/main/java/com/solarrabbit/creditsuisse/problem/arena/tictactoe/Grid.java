package com.solarrabbit.creditsuisse.problem.arena.tictactoe;

public class Grid {
    private static char[][] grid = new char[3][3];

    private static boolean isWon = false;

    private static boolean isTied = false;

    public static void reset() {
        grid = new char[3][3];
        isWon = false;
        isTied = false;
    }

    public static boolean isFilled(int row, int col) {
        // return grid[row][col] != null;
        return false;
    }

    public static void putSymbol(char player, Position position) {
        int row;
        int col;
        switch (position) {
            case NW:
                row = 0;
                col = 0;
                break;
            case N:
                row = 0;
                col = 1;
                break;
            case NE:
                row = 0;
                col = 2;
                break;
            case W:
                row = 1;
                col = 0;
                break;
            case C:
                row = 1;
                col = 1;
                break;
            case E:
                row = 1;
                col = 2;
                break;
            case SW:
                row = 2;
                col = 0;
                break;
            case S:
                row = 2;
                col = 1;
                break;
            case SE:
                row = 2;
                col = 2;
                break;
            default:
                return;
        }

        if (isFilled(row, col)) {
            isWon = true;
            return;
        }

        grid[row][col] = player;
    }
}