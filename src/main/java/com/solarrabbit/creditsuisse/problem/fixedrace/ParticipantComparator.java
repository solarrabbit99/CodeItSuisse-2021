package com.solarrabbit.creditsuisse.problem.fixedrace;

import java.util.Comparator;

public class ParticipantComparator implements Comparator<Participant> {
    @Override
    public int compare(Participant p1, Participant p2) {
        return p2.getOccurrence() - p1.getOccurrence();
    }
}