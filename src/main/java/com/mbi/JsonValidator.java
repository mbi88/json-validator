package com.mbi;

import com.jayway.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonValidator {

    public void validate(JSONObject schema, JSONObject jsonObject) {
        Validator validator = new JsonObjectValidator();
        validator.compareWithSchema(schema, jsonObject);
    }

    public void validate(JSONObject schema, JSONArray jsonArray) {
        Validator validator = new JsonArrayValidator();
        validator.compareWithSchema(schema, jsonArray);
    }

    public void validate(JSONObject schema, Response response) {
        Validator validator = new ResponseValidator();
        validator.compareWithSchema(schema, response);
    }

    public void validate(JSONObject schema, String string) {
        Validator validator = new StringValidator();
        validator.compareWithSchema(schema, string);
    }
}
