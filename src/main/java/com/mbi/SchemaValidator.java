package com.mbi;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;

class SchemaValidator {
    void validateSchema(JSONObject schemaJson, JSONObject jsonObject) {
        Schema schema = SchemaLoader.load(schemaJson);
        schema.validate(jsonObject);
    }
}
