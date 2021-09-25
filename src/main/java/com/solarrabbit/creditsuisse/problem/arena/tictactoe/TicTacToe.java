package com.solarrabbit.creditsuisse.problem.arena.tictactoe;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class TicTacToe {
    private static final String arenaEndpoint = "https://cis2021-arena.herokuapp.com/tic-tac-toe/";
    private String battleId;
    private Player player;

    public void setBattleId(String battleId) {
        this.battleId = battleId;
    }

    public void getIdentity() {
        WebClient client = WebClient.create("https://cis2021-arena.herokuapp.com/tic-tac-toe/start");
        Mono<Player> whatsMyIdentity = client.get().uri(battleId).retrieve().bodyToMono(Player.class);
        whatsMyIdentity.subscribe(myIdentity -> player = myIdentity);

    }

    public void play() {
        InitialResponse response = new InitialRequest(battleId).getResponse();
        System.out.println(response.getPlayer());
    }

    @Override
    public String toString() {
        return String.format("{\"battleId\": %s}", battleId);
    }

}
