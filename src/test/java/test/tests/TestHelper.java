package test.tests;

import com.mbi.JsonValidator;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

class TestHelper {

    private final JsonValidator validator = new JsonValidator();

    private boolean tryValidate(final JSONObject schema, final Object object) {
        try {
            switch (object) {
                case String str -> validator.validate(schema, str);
                case Response rsp -> validator.validate(schema, rsp);
                case JSONObject jo -> validator.validate(schema, jo);
                case JSONArray ja -> validator.validate(schema, ja);
                case null, default -> {
                    return false;
                }
            }
            return true;
        } catch (Throwable throwable) {
            return false;
        }
    }

    <T> void checkFail(final JSONObject schema, final T object) {
        if (tryValidate(schema, object)) throw new AssertionError("Validation unexpectedly passed");
    }

    <T> void checkSuccess(final JSONObject schema, final T object) {
        if (!tryValidate(schema, object)) throw new AssertionError("Validation failed");
    }
}
