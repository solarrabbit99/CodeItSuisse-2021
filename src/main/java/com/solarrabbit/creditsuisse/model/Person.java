package com.solarrabbit.creditsuisse.model;

import org.json.JSONObject;

/**
 * Example class for the use of {@link JSONableModel}.
 */
public class Person implements JSONableModel {
    private final String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void loadJSON(JSONObject object) {
        object.put("name", name);
        object.put("age", age);
    }

    public void ageByOne() {
        this.age++;
    }

}
