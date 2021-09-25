package com.solarrabbit.creditsuisse.problem.decoder;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class Decoder {
  private ArrayList<String> possibleValues;
  private int numSlots;

  public JSONObject solve() {
    JSONObject guess = new JSONObject();

    return guess;
  }

  public void setPossibleValues(List<String> possible_values) {
    possibleValues = new ArrayList<>(possible_values);
  }

  public void setNumSlots(int num_slots) {
    numSlots = num_slots;
  }
}