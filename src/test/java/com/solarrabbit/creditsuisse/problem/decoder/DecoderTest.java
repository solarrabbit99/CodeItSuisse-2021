package com.solarrabbit.creditsuisse.problem.decoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class DecoderTest {
    @Test
    public void testConsistency() {
        History history = new History(Arrays.asList("a", "b", "a", "d"), 12);
        assertTrue(history.isConsistent(new String[] { "a", "a", "a", "b" }));
    }

    @Test
    public void testConsistency1() {
        History history = new History(Arrays.asList("z", "a", "d", "a"), 11);
        assertTrue(history.isConsistent(new String[] { "z", "c", "a", "c" }));
    }

    @Test
    public void testConsistency2() {
        History history = new History(Arrays.asList("e", "e", "e", "e"), 0);
        assertTrue(history.isConsistent(new String[] { "a", "b", "c", "d" }));
    }

    @Test
    public void testConsistency3() {
        History history = new History(Arrays.asList("e", "e", "e", "a"), 10);
        assertTrue(history.isConsistent(new String[] { "a", "b", "c", "d" }));
    }

    @Test
    public void testConsistency4() {
        History history = new History(Arrays.asList("a", "e", "e", "e"), 1);
        assertTrue(history.isConsistent(new String[] { "a", "b", "c", "d" }));
    }

    @Test
    public void testConsistency5() {
        History history = new History(Arrays.asList("a", "b", "c", "d"), 4);
        assertTrue(history.isConsistent(new String[] { "a", "b", "c", "d" }));
    }
}
