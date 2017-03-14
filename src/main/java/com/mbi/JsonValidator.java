package com.mbi;

import com.jayway.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonValidator {

    /**
     * Method to compare JSONObject schemas with JSONObject
     *
     * @param _schema     JSONObject schema
     * @param _jsonObject JSONObject to compare
     */
    public void validate(JSONObject _schema, JSONObject _jsonObject) {
        Validator<JSONObject> validator = (schema, jsonObject) -> new Comparator().compareJsons(schema, jsonObject);

        validator.compareWithSchema(_schema, _jsonObject);
    }

    /**
     * Method to compare JSONObject schemas with JSONArray
     *
     * @param _schema    JSONObject schema
     * @param _jsonArray JSONArray to compare
     */
    public void validate(JSONObject _schema, JSONArray _jsonArray) {
        Validator<JSONArray> validator = (schema, jsonArray) -> {
            for (Object o : jsonArray) {
                Comparator comparator = new Comparator();
                comparator.compareJsons(schema, new JSONObject(o.toString()));
            }
        };

        validator.compareWithSchema(_schema, _jsonArray);
    }

    /**
     * Method to compare JSONObject schemas with rest-assured response
     *
     * @param _schema   JSONObject schema
     * @param _response rest-assured response object to compare
     */
    public void validate(JSONObject _schema, Response _response) {
        Validator<Response> validator = (schema, response) -> {
            if (response.asString().startsWith("{")) {
                validate(schema, new JSONObject(response.asString()));
            } else if (response.asString().startsWith("[")) {
                validate(schema, new JSONArray(response.asString()));
            } else {
                throw new JSONException("Invalid json: " + response.asString());
            }
        };

        validator.compareWithSchema(_schema, _response);
    }

    /**
     * Method to compare JSONObject schemas with String
     *
     * @param _schema JSONObject schema
     * @param _string string object to compare
     */
    public void validate(JSONObject _schema, String _string) {
        Validator<String> validator = (schema, string) -> {
            if (string.startsWith("{")) {
                validate(schema, new JSONObject(string));
            } else if (string.startsWith("[")) {
                validate(schema, new JSONArray(string));
            } else {
                throw new JSONException("Invalid json: " + string);
            }
        };

        validator.compareWithSchema(_schema, _string);
    }
}
