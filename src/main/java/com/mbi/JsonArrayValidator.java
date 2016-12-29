package com.mbi;

import org.json.JSONArray;
import org.json.JSONObject;

class JsonArrayValidator implements Validator {

    @Override
    public <T> void compareWithSchema(JSONObject schema, T object) {
        JSONArray jsonArray = (JSONArray) object;
        for (Object o : jsonArray) {
            Comparator comparator = new Comparator();
            comparator.compareJsons(schema, new JSONObject(o.toString()));
        }
    }
}
