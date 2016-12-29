package com.mbi;

import org.json.JSONObject;

interface Validator {
    <T> void compareWithSchema(JSONObject schema, T object);
}
