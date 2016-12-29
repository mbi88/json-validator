package test;

import com.mbi.Validator;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class TestClass {

    private Validator validator = new Validator();

    @Test
    public void test1() {
        JSONObject jsonObject1 = new JSONObject("{\"a\": 1, \"b\": 3}");
        JSONObject jsonObject2 = new JSONObject("{\"b\": 1, \"a\": 3}");

        validator.validate(jsonObject1, jsonObject2);
        validator.validate(jsonObject2, jsonObject1);
    }

    @Test
    public void test2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\": 1}");
        JSONObject jsonObject2 = new JSONObject("{\"b\": 1, \"a\": 3}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void test3() {
        JSONObject jsonObject1 = new JSONObject("{\"a\": 1}");
        JSONObject jsonObject2 = new JSONObject("{\"b\": 1, \"a\": 3}");

        checkIsFailed(jsonObject2, jsonObject1);
    }

    @Test
    public void test4() {
        JSONObject jsonObject1 = new JSONObject("{\"a\": 2, \"b\": 2}");
        JSONObject jsonObject2 = new JSONObject("{\"a\": 3, \"b\": 2, \"c\":1}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void test5() {
        JSONObject jsonObject1 = new JSONObject("{\"a\": 2, \"b\": 2}");
        JSONObject jsonObject2 = new JSONObject("{\"a\": 3, \"b\": 2, \"c\":1}");

        checkIsFailed(jsonObject2, jsonObject1);
    }

    @Test
    public void test6() {
        JSONObject jsonObject1 = new JSONObject("{\"a\": 3, \"b\": 1, \"c\":1}");
        JSONObject jsonObject2 = new JSONObject("{\"a\": 3, \"b\": null, \"c\":1}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void test7() {
        JSONObject jsonObject1 = new JSONObject("{\"a\": 3, \"b\": 1, \"c\":1}");
        JSONObject jsonObject2 = new JSONObject("{\"a\": 3, \"b\": null, \"c\":1}");

        validator.validate(jsonObject2, jsonObject1);
    }

    @Test
    public void test8() {
        JSONObject jsonObject1 = new JSONObject("{\"a\": 2, \"b\": [{\"a\": 3, \"b\": {\"a\": 3, \"b\": \"s\"}}]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\": 3, \"b\": [{\"a\": 3, \"b\": {\"a\": \"3\", \"b\": \"s\"}}]}");

        checkIsFailed(jsonObject2, jsonObject1);
    }

    private void checkIsFailed(JSONObject j1, JSONObject j2) {
        boolean failed = true;
        try {
            validator.validate(j1, j2);
            failed = false;
        } catch (AssertionError ignored) {
        }
        if (!failed)
            throw new AssertionError();
    }
}
