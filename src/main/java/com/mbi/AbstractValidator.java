package com.mbi;

import org.json.JSONObject;

interface AbstractValidator {
    <T> void compareWithSchema(JSONObject schema, T object);
}
