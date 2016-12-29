package com.mbi;

import com.jayway.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class ResponseValidator implements Validator {

    @Override
    public <T> void compareWithSchema(JSONObject schema, T object) {
        Response actualResult = (Response) object;
        if (actualResult.asString().startsWith("{")) {
            new JsonObjectValidator().compareWithSchema(schema, new JSONObject(actualResult.asString()));
        } else if (actualResult.asString().startsWith("[")) {
            new JsonArrayValidator().compareWithSchema(schema, new JSONArray(actualResult.asString()));
        } else {
            throw new JSONException("Invalid json: " + actualResult.asString());
        }
    }
}
