package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String LOG_TAG = JsonUtils.class.getSimpleName();
    private static final String NAME = "name";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject sandwichJson = new JSONObject(json);
            JSONObject nameJson = sandwichJson.getJSONObject(NAME);
            String name = nameJson.getString(MAIN_NAME);
            JSONArray alsoKnownAsArray  = nameJson.getJSONArray(ALSO_KNOWN_AS);
            List<String> alsoKnownAs = populateList(alsoKnownAsArray);
            String placeOfOrigin = sandwichJson.getString(PLACE_OF_ORIGIN);
            String description = sandwichJson.getString(DESCRIPTION);
            String image = sandwichJson.getString(IMAGE);
            JSONArray ingredientArray = sandwichJson.getJSONArray(INGREDIENTS);
            List<String> ingredients = populateList(ingredientArray);
            return new Sandwich(name, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Exception for parsing json", e);
        }
        return null;
    }

    private static List<String> populateList(JSONArray jsonArray) throws JSONException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }
}
