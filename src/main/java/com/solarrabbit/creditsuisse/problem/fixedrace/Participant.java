package com.solarrabbit.creditsuisse.problem.fixedrace;

public class Participant {
    private String name;
    private int occurrence;

    public Participant(String name, int occurrence) {
        this.name = name;
        this.occurrence = occurrence;
    }

    public int getOccurrence() {
        return occurrence;
    }

    @Override
    public String toString() {
        return name;
    }
}