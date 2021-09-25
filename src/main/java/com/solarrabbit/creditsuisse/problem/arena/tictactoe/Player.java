package com.solarrabbit.creditsuisse.problem.arena.tictactoe;

public class Player {
    private String youAre; // O or X
    private String id;

    public void setPlayerRole(String youAre) {
        this.youAre = youAre;
    }

    public void setPlayerId(String id) {
        this.id = id;
    }

    public String flipTable() {
        return PlayerAction.flipTable();
    }

    // Return json response
    public String putSymbol(String position) {
        return PlayerAction.putSymbol(position);
    }

    public void play() {
        if (youAre == "O") { // POST first move
            flipTable();
            return;
        }

        // Wait for bot response
    }
}
