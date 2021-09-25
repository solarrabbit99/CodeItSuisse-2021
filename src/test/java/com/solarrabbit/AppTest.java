package com.solarrabbit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.solarrabbit.creditsuisse.problem.parasite.ParasiteX;
import com.solarrabbit.creditsuisse.problem.parasite.Room;

import org.junit.Test;

public class AppTest {

    @Test
    public void test() {
        Integer[] arr = new Integer[1];
        assertEquals(null, arr[0]);
    }

    @Test
    public void test2() {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(0, 3, 2));
        list.add(Arrays.asList(0, 1, 1));
        list.add(Arrays.asList(1, 0, 0));
        Room room = new Room(list);
        ParasiteX x = new ParasiteX(room);
        assertEquals(1, x.getTotalTick());
    }

}
