package com.solarrabbit.creditsuisse.problem.arena.tictactoe;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class InitialRequest {
    private static final String URL = "https://cis2021-arena.herokuapp.com/tic-tac-toe/start/";
    private static final RestTemplate TEMPLATE = new RestTemplateBuilder().build();
    private final String uuid;

    public InitialRequest(String uuid) {
        this.uuid = uuid;
    }

    public InitialResponse getResponse() {
        String url = URL + uuid;
        ResponseEntity<InitialResponse> response = TEMPLATE.getForEntity(url, InitialResponse.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }

}
