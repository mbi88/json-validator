package com.mbi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class StringValidator implements Validator {

    @Override
    public <T> void compareWithSchema(JSONObject schema, T object) {
        String actualResult = (String) object;
        if (actualResult.startsWith("{")) {
            new JsonObjectValidator().compareWithSchema(schema, new JSONObject(actualResult));
        } else if (actualResult.startsWith("[")) {
            new JsonArrayValidator().compareWithSchema(schema, new JSONArray(actualResult));
        } else {
            throw new JSONException("Invalid json: " + actualResult);
        }
    }
}
