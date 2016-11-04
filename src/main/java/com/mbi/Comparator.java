package com.mbi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static org.testng.Assert.assertEquals;

/**
 * Created by mbi on 11/4/16.
 */
class Comparator {

    /**
     * ...
     *
     * @param schema   ...
     * @param response ...
     */
    void compareJsons(JSONObject schema, JSONObject response) {
        compare(schema, response, true);
        compare(response, schema, false);
    }

    /**
     * ...
     *
     * @param schema          ...
     * @param response        ...
     * @param acceptAllIfNull if true - comparing: {"a": null} with {"a": 1} is ok
     */
    private void compare(JSONObject schema, JSONObject response, boolean acceptAllIfNull) {
        for (String schemaField : JSONObject.getNames(schema)) {
            findField(schemaField, response);
            compareValueType(schemaField, schema, response, acceptAllIfNull);
            compareNestedJsons(schemaField, schema, response, acceptAllIfNull);
        }
    }

    /**
     * ...
     *
     * @param field ...
     * @param json  ...
     */
    private void findField(String field, JSONObject json) {
        try {
            json.get(field);
        } catch (JSONException e) {
            String errorMessage = ("Field is missing: ").concat(e.getMessage().substring(10));
            throw new AssertionError(errorMessage);
        }
    }

    /**
     * ...
     *
     * @param field           ...
     * @param schema          ...
     * @param response        ...
     * @param acceptAllIfNull ...
     */
    private void compareValueType(String field, JSONObject schema, JSONObject response, boolean acceptAllIfNull) {
        try {
            if (acceptAllIfNull && !schema.get(field).getClass().getSimpleName().equalsIgnoreCase("null"))
                assertEquals(schema.get(field).getClass(), response.get(field).getClass());
        } catch (AssertionError assertionError) {
            String errorMessage = field.concat(" has incorrect type: ").concat(assertionError.getMessage());
            throw new AssertionError(errorMessage);
        }
    }

    private void compareNestedJsons(String schemaField, JSONObject schema, JSONObject response, boolean acceptAllIfNull) {
        compareNestedObjects(schemaField, schema, response, acceptAllIfNull);
        compareNestedArrays(schemaField, schema, response, acceptAllIfNull);
    }

    private void compareNestedObjects(String schemaField, JSONObject schema, JSONObject response, boolean acceptAllIfNull) {
        try {
            compare(schema.getJSONObject(schemaField), response.getJSONObject(schemaField), acceptAllIfNull);
        } catch (JSONException ignored) {
        }
    }

    private void compareNestedArrays(String schemaField, JSONObject schema, JSONObject response, boolean acceptAllIfNull) {
        try {
            JSONArray schemaArray = schema.getJSONArray(schemaField);
            JSONArray responseArray = response.getJSONArray(schemaField);
            for (Object so : schemaArray) {
                JSONObject sJson = new JSONObject(so.toString());
                for (Object ro : responseArray) {
                    JSONObject rJson = new JSONObject(ro.toString());
                    compare(sJson, rJson, acceptAllIfNull);
                }
            }
        } catch (JSONException ignored) {
        }
    }
}
