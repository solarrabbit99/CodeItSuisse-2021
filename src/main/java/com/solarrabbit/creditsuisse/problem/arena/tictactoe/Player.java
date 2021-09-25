package com.solarrabbit.creditsuisse.problem.arena.tictactoe;

public class Player {
    private final String youAre;
    private final String id;

    public Player(String youAre, String id) {
        this.youAre = youAre;
        this.id = id;
    }

    public String flipTable() {
        return PlayerAction.flipTable();
    }

    // Return json response
    public String putSymbol(String position) {
        return PlayerAction.putSymbol(position);
    }
}
