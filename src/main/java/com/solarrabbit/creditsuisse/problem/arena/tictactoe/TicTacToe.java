package com.solarrabbit.creditsuisse.problem.arena.tictactoe;

public class TicTacToe {
    private static final String arenaEndpoint = "https://cis2021-arena.herokuapp.com/tic-tac-toe/";
    private String battleId;

    // @GetMapping(arenaEndpoint + battleId)
    // public Stream getRequest() {
    // return null;
    // }

    public void setBattleId(String battleId) {
        this.battleId = battleId;
    }

    @Override
    public String toString() {
        return String.format("{\"battleId\": %s}", battleId);
    }

}
