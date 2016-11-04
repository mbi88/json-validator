package test;

import com.mbi.JsonValidator;
import org.json.JSONObject;
import org.testng.annotations.Test;

/**
 * Created by mbi on 11/4/16.
 */
public class TestClass {

    private JsonValidator validator = new JsonValidator();
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

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test
    public void test3() {
        JSONObject jsonObject1 = new JSONObject("{\"a\": 1}");
        JSONObject jsonObject2 = new JSONObject("{\"b\": 1, \"a\": 3}");

        validator.validate(jsonObject2, jsonObject1);
    }

    @Test
    public void test4() {
        JSONObject jsonObject1 = new JSONObject("{\"a\": 2, \"b\": 2}");
        JSONObject jsonObject2 = new JSONObject("{\"a\": 3, \"b\": 2, \"c\":1}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test
    public void test5() {
        JSONObject jsonObject1 = new JSONObject("{\"a\": 2, \"b\": 2}");
        JSONObject jsonObject2 = new JSONObject("{\"a\": 3, \"b\": 2, \"c\":1}");

        validator.validate(jsonObject2, jsonObject1);
    }

    @Test
    public void test6() {
        JSONObject jsonObject1 = new JSONObject("{\"a\": 3, \"b\": 1, \"c\":1}");
        JSONObject jsonObject2 = new JSONObject("{\"a\": 3, \"b\": null, \"c\":1}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test
    public void test7() {
        JSONObject jsonObject1 = new JSONObject("{\"a\": 2, \"b\": [{\"a\": 3, \"b\": {\"a\": 3, \"b\": \"s\"}}]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\": 3, \"b\": [{\"a\": 3, \"b\": {\"a\": \"3\", \"b\": \"s\"}}]}");

        validator.validate(jsonObject2, jsonObject1);
    }
}
