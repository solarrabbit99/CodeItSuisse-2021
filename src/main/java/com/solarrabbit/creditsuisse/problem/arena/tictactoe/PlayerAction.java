package com.solarrabbit.creditsuisse.problem.arena.tictactoe;

public class PlayerAction {
    private static final String flipTable = "(╯°□°)╯︵ ┻━┻";

    public static String flipTable() {
        return String.format("{\"action\" : %s}", flipTable);
    }

    public static String putSymbol(String position) {
        return String.format("{\"action\" : \"putSymbol\",\"positon\": %s}", position);
    }
}