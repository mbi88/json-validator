package com.mbi;

import org.json.JSONObject;

/**
 * Created by mbi on 11/4/16.
 */
public interface Validator {

    /**
     * ...
     *
     * @param schema   ...
     * @param response ...
     * @param <T>      ...
     */
    <T> void validate(JSONObject schema, T response);
}
