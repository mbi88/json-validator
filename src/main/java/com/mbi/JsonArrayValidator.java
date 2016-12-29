package com.mbi;

import org.json.JSONArray;
import org.json.JSONObject;

class JsonArrayValidator implements AbstractValidator {

    @Override
    public <T> void compareWithSchema(JSONObject schema, T object) {
        JSONArray jsonArray = new JSONArray(object.toString());
        for (Object o : jsonArray) {
            Comparator comparator = new Comparator();
            comparator.compareJsons(schema, new JSONObject(o.toString()));
        }
    }
}
