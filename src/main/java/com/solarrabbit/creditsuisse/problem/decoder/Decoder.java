package com.solarrabbit.creditsuisse.problem.decoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.json.JSONArray;
import org.json.JSONObject;

public class Decoder {
    private static List<List<String>> list = null;
    private final List<String> possible_values;
    private final int num_slots;
    private final List<History> history;

    public Decoder(List<String> possible_values, int num_slots, List<History> history) {
        this.possible_values = possible_values;
        this.num_slots = num_slots;
        this.history = history;
    }

    public JSONObject getOutput() {
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray(getNext());
        object.put("answer", array);
        return object;
    }

    public List<String> getNext() {
        if (list == null || LocalDateTime.now().getMinute() % 10 == 0) {
            generateNewList();
        }
        return list.get(LocalDateTime.now().getMinute() % 10);
    }

    private void generateNewList() {
        List<List<String>> newList = Stream.of(possible_values.toArray(new String[0])).map(str -> {
            List<String> lst = new ArrayList<>();
            lst.add(str);
            return lst;
        }).collect(Collectors.toList());
        list = getStream(newList, num_slots - 1).stream().filter(lst -> isConsistent(lst)).limit(10)
                .collect(Collectors.toList());
    }

    private List<List<String>> getStream(List<List<String>> list, int calls) {
        if (calls <= 0)
            return list;
        List<List<String>> newList = Stream.of(possible_values.toArray(new String[0]))
                .flatMap(str -> list.stream().map(lst -> {
                    List<String> newLst = new ArrayList<>(lst);
                    newLst.add(str);
                    return newLst;
                })).collect(Collectors.toList());
        return getStream(newList, calls - 1);
    }

    private boolean isConsistent(String[] expected) {
        for (History h : history) {
            if (!h.isConsistent(expected))
                return false;
        }
        return true;
    }

    private boolean isConsistent(List<String> expected) {
        return isConsistent(expected.toArray(new String[0]));
    }

}