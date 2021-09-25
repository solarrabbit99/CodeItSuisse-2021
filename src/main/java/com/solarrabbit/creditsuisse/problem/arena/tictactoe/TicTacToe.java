package com.solarrabbit.creditsuisse.problem.arena.tictactoe;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

public class TicTacToe {
    private static final String arenaEndpoint = "https://cis2021-arena.herokuapp.com/tic-tac-toe/";
    private String battleId;

    public void getRequest() {
        WebClient client = WebClient.create("https://cis2021-arena.herokuapp.com/tic-tac-toe/start");
        // Flux<ServerSentEvent<Object>> eventStream =
        // client.get().uri(battleId).retrieve().bodyToFlux(ArenaEvent.class);
    }

    public void setBattleId(String battleId) {
        this.battleId = battleId;
    }

    @Override
    public String toString() {
        return String.format("{\"battleId\": %s}", battleId);
    }

}
