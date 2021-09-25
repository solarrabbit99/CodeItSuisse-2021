package com.solarrabbit.creditsuisse.problem.arena.tictactoe;

public class TicTacToe {
    private static final String arenaEndpoint = "https://cis2021-arena.herokuapp.com/tic-tac-toe/";
    private final String battleId;

    public TicTacToe(String battleId) {
        this.battleId = battleId;
    }

    // public Stream getRequest() {
    // return null;
    // }

    @Override
    public String toString() {
        return battleId;
    }
}
