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

    @Test
    public void test2() {
        AsteroidProblem problem = new AsteroidProblem("BBAAABBB");
        JSONObject answer = problem.solve();
        try {
            assertEquals(8, answer.getInt("score"));
            assertEquals(3, answer.getInt("origin"));
        } catch (JSONException exception) {
            fail();
        }
    }

    @Test
    public void test3() {
        AsteroidProblem problem = new AsteroidProblem("CCCAAAAABBBAAACCC");
        JSONObject answer = problem.solve();
        try {
            assertEquals(21, answer.getInt("score"));
            assertEquals(9, answer.getInt("origin"));
        } catch (JSONException exception) {
            fail();
        }
    }

    @Test
    public void test4() {
        AsteroidProblem problem = new AsteroidProblem("ABBBBA");
        JSONObject answer = problem.solve();
        try {
            assertEquals(6, answer.getInt("score"));
            assertEquals(2, answer.getInt("origin"));
        } catch (JSONException exception) {
            fail();
        }
    }

    @Test
    public void test5() {
        AsteroidProblem problem = new AsteroidProblem("ABA");
        JSONObject answer = problem.solve();
        try {
            assertEquals(3, answer.getInt("score"));
            assertEquals(1, answer.getInt("origin"));
        } catch (JSONException exception) {
            fail();
        }
    }

}
