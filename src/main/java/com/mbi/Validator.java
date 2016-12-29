package com.mbi;

import com.jayway.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class Validator {

    public void validate(JSONObject schema, JSONObject jsonObject) {
        AbstractValidator validator = new JsonObjectValidator();
        validator.compareWithSchema(schema, jsonObject);
    }

    public void validate(JSONObject schema, JSONArray jsonArray) {
        AbstractValidator validator = new JsonArrayValidator();
        validator.compareWithSchema(schema, jsonArray);
    }

    public void validate(JSONObject schema, Response response) {
        AbstractValidator validator = new ResponseValidator();
        validator.compareWithSchema(schema, response);
    }

    public void validate(JSONObject schema, String string) {
        AbstractValidator validator = new StringValidator();
        validator.compareWithSchema(schema, string);
    }
}
