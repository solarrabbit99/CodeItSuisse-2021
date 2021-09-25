package com.solarrabbit.creditsuisse.problem.decoder;

import java.util.List;

public class History {
    private List<String> outputReceived;
    private int result;

    public void setOutputReceived(List<String> output_received) {
        outputReceived = output_received;
    }

    public void setResult(int result) {
        this.result = result;
    }
}