package com.solarrabbit.creditsuisse.problem.fixedrace;

import java.util.HashMap;
import java.util.PriorityQueue;

public class FixedRace {
    private static HashMap<String, Integer> map = new HashMap<>();

    public static String solve(String input) {
        String[] names = input.split(",");
        String res = "";

        PriorityQueue<Participant> pq = new PriorityQueue<>(10, new ParticipantComparator());

        for (String name : names) { // Count occurrences. High occurrence = faster
            map.putIfAbsent(name, 0);
            map.put(name, map.get(name) + 1);
        }

        for (String name : names) { // Put names in the queue. High occurence = higher priority
            pq.add(new Participant(name, map.get(name)));
        }

        while (!pq.isEmpty()) { // Build result
            res += (pq.remove().toString() + ",");
        }

        return res.replaceAll(".$", ""); // removes the trailing comma
    }
}