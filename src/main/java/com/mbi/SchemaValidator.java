package com.mbi;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;

class SchemaValidator {
    void validateSchema(JSONObject schemaJson, JSONObject jsonObject) {
        Schema schema = getSchema(schemaJson);
        try {
            schema.validate(jsonObject);
        } catch (ValidationException ve) {
            // Add error message
            String msg = ve.getMessage();
            // Add all violations
            for (String s : ve.getAllMessages()) {
                msg = msg.concat("\n" + s);
            }
            // Add object
            msg = msg.concat("\n\nResponse: " + jsonObject.toString(2));

            throw new ValidationException(ve.getViolatedSchema(), msg, ve.getKeyword());
        }
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
