package com.mbi;

import org.json.JSONObject;

class JsonObjectValidator implements Validator {

    @Override
    public <T> void compareWithSchema(JSONObject schema, T object) {
        JSONObject jsonObject = (JSONObject) object;
        Comparator comparator = new Comparator();
        comparator.compareJsons(schema, jsonObject);
    }
}
