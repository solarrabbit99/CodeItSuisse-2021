package com.solarrabbit.creditsuisse.problem.asteroid;

import java.util.List;

import org.json.JSONArray;

public class AsteroidWrapper {
    private List<String> test_cases;

    public JSONArray solve() {
        JSONArray answer = new JSONArray();
        test_cases.forEach(prob -> answer.put(new AsteroidProblem(prob).solve()));
        return answer;
    }

    public void setTest_cases(List<String> strings) {
        this.test_cases = strings;
    }

}
