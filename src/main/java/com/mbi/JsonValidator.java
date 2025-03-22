package com.mbi;

import io.restassured.response.Response;
import org.apache.commons.lang3.Validate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.mbi.Constants.*;

/**
 * A universal validator for comparing different types of JSON data against a JSON schema.
 * Supported types:
 * - {@link JSONObject}
 * - {@link JSONArray}
 * - {@link Response} (from Rest Assured)
 * - {@link String} (must be valid JSON)
 *
 * @see <a href="https://json-schema.org/">JSON Schema</a>
 * @see <a href="https://jsonschema.net/">Schema Generator</a>
 * @see <a href="https://github.com/everit-org/json-schema">Everit JSON Schema Validator</a>
 */
public final class JsonValidator {

    private final SchemaValidator validator = new SchemaValidator();

    /**
     * Validates a JSONObject against a JSON schema.
     *
     * @param schema the JSON schema
     * @param json   the JSONObject to validate
     * @throws AssertionError if any argument is null
     */
    public void validate(final JSONObject schema, final JSONObject json) {
        Validate.notNull(schema, JSON_SCHEMA_NULL_ERROR_MESSAGE);
        Validate.notNull(json, VALIDATION_JSON_NULL_ERROR_MESSAGE);

        final Comparator<JSONObject> comparator = validator::validateSchema;
        comparator.compareWithSchema(schema, json);
    }

    /**
     * Validates a JSONArray against a JSON schema.
     *
     * @param schema the JSON schema
     * @param json   the JSONArray to validate
     * @throws AssertionError if any argument is null
     */
    public void validate(final JSONObject schema, final JSONArray json) {
        Validate.notNull(schema, JSON_SCHEMA_NULL_ERROR_MESSAGE);
        Validate.notNull(json, VALIDATION_JSON_NULL_ERROR_MESSAGE);

        final Comparator<JSONArray> comparator = validator::validateSchema;
        comparator.compareWithSchema(schema, json);
    }

    /**
     * Validates a Rest-Assured Response body against a JSON schema.
     *
     * @param schema the JSON schema
     * @param json   the HTTP response
     * @throws AssertionError if any argument is null
     * @throws JSONException  if the response is not valid JSON
     */
    public void validate(final JSONObject schema, final Response json) {
        Validate.notNull(schema, JSON_SCHEMA_NULL_ERROR_MESSAGE);
        Validate.notNull(json, VALIDATION_JSON_NULL_ERROR_MESSAGE);

        final Comparator<Response> comparator = (schm, rsp) -> {
            if (isJsonObject(rsp.asString())) {
                validate(schm, new JSONObject(rsp.asString()));
            } else if (isJsonArray(rsp.asString())) {
                validate(schm, new JSONArray(rsp.asString()));
            } else {
                throw new JSONException(INVALID_JSON_ERROR_MESSAGE + rsp.asString());
            }
        };

        comparator.compareWithSchema(schema, json);
    }

    /**
     * Validates a raw JSON string against a JSON schema.
     *
     * @param schema the JSON schema
     * @param json   the JSON string
     * @throws AssertionError if any argument is null
     * @throws JSONException  if the string is not valid JSON
     */
    public void validate(final JSONObject schema, final String json) {
        Validate.notNull(schema, JSON_SCHEMA_NULL_ERROR_MESSAGE);
        Validate.notNull(json, VALIDATION_JSON_NULL_ERROR_MESSAGE);

        final Comparator<String> comparator = (schm, str) -> {
            if (isJsonObject(str)) {
                validate(schm, new JSONObject(str));
            } else if (isJsonArray(str)) {
                validate(schm, new JSONArray(str));
            } else {
                throw new JSONException(INVALID_JSON_ERROR_MESSAGE + str);
            }
        };

        comparator.compareWithSchema(schema, json);
    }

    /**
     * Checks if a string starts with '{', indicating a JSON object.
     *
     * @param s the input string
     * @return true if the string is likely a JSON object
     */
    @SuppressWarnings("PMD.SimplifyStartsWith")
    private boolean isJsonObject(final String s) {
        return s.trim().startsWith("{");
    }

    /**
     * Checks if a string starts with '[', indicating a JSON array.
     *
     * @param s the input string
     * @return true if the string is likely a JSON array
     */
    @SuppressWarnings("PMD.SimplifyStartsWith")
    private boolean isJsonArray(final String s) {
        return s.trim().startsWith("[");
    }
}
