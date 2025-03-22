package com.mbi;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Validates json data according to json schema.
 */
final class SchemaValidator {

    private final ExceptionMessageCompositor compositor = new ExceptionMessageCompositor();

    /**
     * Validates JSON object or array against the given JSON schema.
     *
     * @param jsonSchema the JSON schema to validate against
     * @param json       the object to validate (must be JSONObject or JSONArray)
     * @param <T>        the type of JSON structure
     * @throws ValidationException if validation fails
     */
    public <T> void validateSchema(final JSONObject jsonSchema, final T json) {
        final var schema = getSchema(jsonSchema);

        try {
            schema.validate(json);
        } catch (ValidationException ve) {
            final var msg = compositor.getMessage(ve, json);
            throw new ValidationException(ve.getViolatedSchema(), msg, ve.getKeyword(), ve.getSchemaLocation());
        }
    }

    /**
     * Reads json schema to schema validator object.
     *
     * @param schemaJson json schema
     * @return schema validator
     * @throws JSONException if schema is incorrect
     */
    private Schema getSchema(final JSONObject schemaJson) {
        if (schemaJson.isEmpty()) {
            throw new JSONException("Invalid schema! Schema is empty.");
        }

        return SchemaLoader.load(schemaJson);
    }
}
