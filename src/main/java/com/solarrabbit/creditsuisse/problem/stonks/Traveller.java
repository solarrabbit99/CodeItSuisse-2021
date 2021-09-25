package com.solarrabbit.creditsuisse.problem.stonks;

import com.solarrabbit.creditsuisse.problem.stonks.StonksProblem.Market;
import com.solarrabbit.creditsuisse.problem.stonks.StonksProblem.Stock;

public class Traveller implements Cloneable {
    private static final int FINAL_YEAR = 2037;
    private int energy;
    private int atYear;
    private int capital;
    private Market market;

    public Traveller(int energy, int capital, Market market) {
        this.energy = energy;
        this.capital = capital;
        this.market = market;
        this.atYear = 2037;
    }

    public Stock getStock(String label) {
        return this.market.get(label);
    }

    public void travelForward() {
        this.atYear++;
        this.energy--;
    }

    public void travelBackward() {
        this.atYear--;
        this.energy--;
    }

    public int atYear() {
        return this.atYear;
    }

    public boolean canTravelPast() {
        return energy >= FINAL_YEAR - this.atYear;
    }

    public boolean shouldSell(String name, Stock stock) {
        return this.market.get(name).getPrice() > stock.getPrice();
    }
}
