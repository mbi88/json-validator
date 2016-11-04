package com.mbi;

import com.jayway.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mbi on 11/4/16.
 */
public class JsonValidator implements Validator {

    public <T> void validate(JSONObject schema, T actualResult) {
        // Response
        if (actualResult instanceof Response) {
            compareWithSchema(schema, (Response) actualResult);
        }
        // JSONArray
        else if (actualResult instanceof JSONArray) {
            compareWithSchema(schema, (JSONArray) actualResult);
        }
        // JSONObject
        else if (actualResult instanceof JSONObject) {
            compareWithSchema(schema, (JSONObject) actualResult);
        }
        // String
        else if (actualResult instanceof String) {
            compareWithSchema(schema, (String) actualResult);
        }
        // Something else
        else {
            throw new IllegalArgumentException(actualResult.toString());
        }
    }

    private void compareWithSchema(JSONObject schema, JSONObject actualResult) {
        Comparator comparator = new Comparator();
        comparator.compareJsons(schema, actualResult);
    }

    private void compareWithSchema(JSONObject schema, JSONArray actualResult) {
        for (Object o : actualResult) {
            Comparator comparator = new Comparator();
            comparator.compareJsons(schema, new JSONObject(o.toString()));
        }
    }

    private void compareWithSchema(JSONObject schema, String actualResult) {
        if (actualResult.startsWith("{")) {
            compareWithSchema(schema, new JSONObject(actualResult));
        } else if (actualResult.startsWith("[")) {
            compareWithSchema(schema, new JSONArray(actualResult));
        } else {
            throw new JSONException("Invalid json: " + actualResult);
        }
    }

    private void compareWithSchema(JSONObject schema, Response actualResult) {
        if (actualResult.asString().startsWith("{")) {
            compareWithSchema(schema, new JSONObject(actualResult.asString()));
        } else if (actualResult.asString().startsWith("[")) {
            compareWithSchema(schema, new JSONArray(actualResult.asString()));
        } else {
            throw new JSONException("Invalid json: " + actualResult.asString());
        }
    }
}
