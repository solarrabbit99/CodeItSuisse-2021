package com.solarrabbit.creditsuisse.problem.stonks;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;

public class StonksProblem {
    private final int energy;
    private final int capital;
    private final Map<Integer, Market> timeline;

    public StonksProblem(int energy, int capital, Map<Integer, Market> timeline) {
        this.energy = energy;
        this.capital = capital;
        this.timeline = timeline;
    }

    public JSONArray solve() {
        JSONArray array = new JSONArray();
        array.put(this.timeline.get(2020).get("Apple").price + "");
        return array;
    }

    public static class Market extends LinkedHashMap<String, Stock> {
        private Set<String> getLabels() {
            return this.keySet();
        }
    }

    public static class Stock {
        private int price;
        private int quantity;

        public void setPrice(int price) {
            this.price = price;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getPrice() {
            return this.price;
        }

        public int getQuantity() {
            return this.quantity;
        }
    }
}
