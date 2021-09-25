package com.solarrabbit.creditsuisse.problem.parasite;

import java.util.ArrayList;
import java.util.List;
import com.solarrabbit.creditsuisse.Solvable;
import org.json.JSONObject;

public class ParasiteProblem implements Solvable {
    private int room;
    private List<List<Integer>> grid;
    private ArrayList<String> interestedIndividuals;

    @Override
    public JSONObject solve() {
        Room room = new Room(grid);
        ParasiteA a = new ParasiteA((Room) room.clone());
        ParasiteB b = new ParasiteB((Room) room.clone());
        ParasiteX x = new ParasiteX((Room) room.clone());

        JSONObject interestsObject = new JSONObject();
        List<int[]> interests = getInterest();
        for (int i = 0; i < interests.size(); i++) {
            int[] indiv = interests.get(i);
            int ticks = a.solve(indiv[0], indiv[1]);
            interestsObject.put(interestedIndividuals.get(i), ticks);
        }

        JSONObject answer = new JSONObject();
        answer.put("room", this.room);
        answer.put("p1", interestsObject);
        answer.put("p2", a.getTotalTick());
        answer.put("p3", b.getTotalTick());
        answer.put("p4", x.getTotalTick());

        return answer;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public void setGrid(List<List<Integer>> grid) {
        this.grid = grid;
    }

    public void setInterestedIndividuals(ArrayList<String> interestedIndividuals) {
        this.interestedIndividuals = interestedIndividuals;
    }

    private List<int[]> getInterest() {
        List<int[]> list = new ArrayList<>();
        for (String individual : interestedIndividuals) {
            String[] coord = individual.split(",");
            int[] intArr = new int[coord.length];
            for (int i = 0; i < coord.length; i++) {
                intArr[i] = Integer.parseInt(coord[i]);
            }
            list.add(intArr);
        }
        return list;
    }
}
