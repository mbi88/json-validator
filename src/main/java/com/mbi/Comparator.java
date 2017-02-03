package com.mbi;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static org.testng.Assert.assertEquals;

class Comparator {

    /**
     * ...
     *
     * @param schema   ...
     * @param response ...
     */
    void compareJsons(JSONObject schema, JSONObject response) {
        // Compare schemas with response json object
        compare(schema, response, true);
        // Compare vice versa
        compare(response, schema, false);
    }

    /**
     * ...
     *
     * @param schema           ...
     * @param response         ...
     * @param isSchemaFirstArg if true - comparing: {"a": null} with {"a": 1} is ok
     */
    private void compare(JSONObject schema, JSONObject response, boolean isSchemaFirstArg) {
        // For each field in schema
        for (String schemaField : JSONObject.getNames(schema)) {
            // Check schema field exists in response object
            findField(schemaField, response, isSchemaFirstArg);
            // Check schema field value type is equal to response's
            compareValueType(schemaField, schema, response, isSchemaFirstArg);
            // Compare nested JSON objects/arrays (if exist)
            compareNestedJsons(schemaField, schema, response, isSchemaFirstArg);
        }
    }

    /**
     * ...
     *
     * @param schemaField ...
     * @param response    ...
     */
    private void findField(String schemaField, JSONObject response, boolean isSchemaFirstArg) {
        try {
            // Try to find out if schema field exists in response json
            response.get(schemaField);
        } catch (JSONException e) {
            String errorMessage;
            if (isSchemaFirstArg) {
                errorMessage = "Field is missing: ";
            } else {
                errorMessage = "Field is redundant: ";
            }

            throw new AssertionError(errorMessage.concat(e.getMessage().substring(10)));
        }
    }

    /**
     * ...
     *
     * @param schemaField      ...
     * @param schema           ...
     * @param response         ...
     * @param isSchemaFirstArg ...
     */
    private void compareValueType(String schemaField, JSONObject schema, JSONObject response, boolean isSchemaFirstArg) {
        // Do not compare vice versa option (if response object is the first arg)
        if (!isSchemaFirstArg)
            return;

        try {
            // Do assertion if schema field value is not null.
            // Skipping null value for ability to make comparision like {"a": null} with {"a": 1}.
            // Schema field "a" accepts all values of response field "a"
            if (!getFieldValueClassName(schemaField, schema).equalsIgnoreCase("null"))
                assertEquals(schema.get(schemaField).getClass(), response.get(schemaField).getClass());
        } catch (AssertionError assertionError) {
            String errorMessage = schemaField.concat(" has incorrect type: ").concat(assertionError.getMessage());
            throw new AssertionError(errorMessage);
        }
    }

    private String getFieldValueClassName(String fieldName, JSONObject json) {
        return json.get(fieldName).getClass().getSimpleName();
    }

    private boolean isEmptyJsonObject(JSONObject json, String field) {
        return json.get(field).toString().equals("{}");
    }

    private boolean isEmptyJsonObject(JSONObject json) {
        return json.toString().equals("{}");
    }

    private boolean isEmptyJsonArray(JSONObject json, String field) {
        return json.get(field).toString().equals("[]");
    }

    private void compareNestedJsons(String schemaField, JSONObject schema, JSONObject response, boolean isSchemaFirstArg) {
        compareNestedObjects(schemaField, schema, response, isSchemaFirstArg);
        compareNestedArrays(schemaField, schema, response, isSchemaFirstArg);
    }

    private void compareNestedObjects(String schemaField, JSONObject schema, JSONObject response, boolean isSchemaFirstArg) {
        // If schema looks like: {"a": {}} - skip comparing
        if (isEmptyJsonObject(schema, schemaField) && isSchemaFirstArg)
            return;
        if (isEmptyJsonObject(response, schemaField) && !isSchemaFirstArg)
            return;

        // Try to compare if objects have nested object
        try {
            compare(schema.getJSONObject(schemaField), response.getJSONObject(schemaField), isSchemaFirstArg);
        } catch (JSONException ignored) {
        }
    }

    private void compareNestedArrays(String schemaField, JSONObject schema, JSONObject response, boolean isSchemaFirstArg) {
        // Try to compare if arrays have nested object
        try {
            JSONArray schemaArray = schema.getJSONArray(schemaField);
            JSONArray responseArray = response.getJSONArray(schemaField);
            for (Object so : schemaArray) {
                JSONObject schemaJson = new JSONObject(so.toString());

                // If schema looks like: {"a": [{}]} - skip comparing
                if (isEmptyJsonObject(schemaJson) && isSchemaFirstArg)
                    return;

                for (Object ro : responseArray) {
                    JSONObject responseJson = new JSONObject(ro.toString());

                    // If schema looks like: {"a": [{}]} - skip comparing
                    if (isEmptyJsonObject(responseJson) && !isSchemaFirstArg)
                        return;

                    compare(schemaJson, responseJson, isSchemaFirstArg);
                }
            }
        } catch (JSONException ignored) {
        }
    }
}
