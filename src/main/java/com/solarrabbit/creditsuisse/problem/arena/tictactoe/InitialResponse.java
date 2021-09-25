package com.solarrabbit.creditsuisse.problem.arena.tictactoe;

public class InitialResponse {
    private final String youAre;
    private final String id;

    public InitialResponse(String youAre, String id) {
        this.youAre = youAre;
        this.id = id;
    }

    public String getPlayer() {
        return youAre;
    }
}
