package com.solarrabbit.creditsuisse.problem.asteroid;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

public class AsteroidTest {

    @Test
    public void test() {
        AsteroidProblem problem = new AsteroidProblem("CCCAAABBBAAACCC");
        JSONObject answer = problem.solve();
        try {
            assertEquals(15, answer.getInt("score"));
            assertEquals(7, answer.getInt("origin"));
        } catch (JSONException exception) {
            fail();
        }
    }

}
