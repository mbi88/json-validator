package com.mbi;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;

class SchemaValidator {
    void validateSchema(JSONObject schemaJson, JSONObject jsonObject) {
        Schema schema = getSchema(schemaJson);
        schema.validate(jsonObject);
    }

    private Schema getSchema(JSONObject schemaJson) {
        Schema schema = SchemaLoader.load(schemaJson);

        if (schema.toString().equalsIgnoreCase("{}")) {
            String message = ("Invalid schema!\n")
                    .concat("Schema:\n")
                    .concat(schema.toString());

            throw new ValidationException(schema, message, "$schema");
        }

        return schema;
    }
}
