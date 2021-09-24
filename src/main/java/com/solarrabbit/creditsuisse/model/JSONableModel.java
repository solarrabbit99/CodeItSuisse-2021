package com.solarrabbit.creditsuisse.model;

import org.json.JSONObject;

/**
 * Encapsulate classes that loads its attributes into a given
 * {@link JSONObject}.
 */
public interface JSONableModel {

    /**
     * Loads attributes into {@code object}.
     *
     * @param object to be loaded
     */
    public void loadJSON(JSONObject object);

}
