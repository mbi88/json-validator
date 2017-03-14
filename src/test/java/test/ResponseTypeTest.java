package test;

import com.jayway.restassured.response.Response;
import com.mbi.JsonValidator;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.get;

/**
 * Created by solg on 11.01.2017.
 */
public class ResponseTypeTest {

    private JsonValidator validator = new JsonValidator();

    @Test
    public void testCompareCorrectType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":1}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874ddc80f0000d806ec8c3c"); //{"a":555}

        validator.validate(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareIncorrectTypeInt() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":1}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874f2c40f0000c908ec8c87"); //{"a":"qwe"}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareIncorrectTypeString() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"qwe\"}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874ddc80f0000d806ec8c3c"); //{"a":555}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareIncorrectBooleanType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":true}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874ddc80f0000d806ec8c3c"); //{"a":555}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareNullValueSchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":null}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874ddc80f0000d806ec8c3c"); //{"a":555}

        validator.validate(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareCorrectBooleanType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":true}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874e3620f00007207ec8c48"); //{"a":false}

        validator.validate(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareNullValueArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":1}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874f33b0f0000d508ec8c8b"); //{"a":null}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareIncorrectNameField() {
        JSONObject jsonObject1 = new JSONObject("{\"b\":555}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874ddc80f0000d806ec8c3c"); //{"a":555}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareFieldsRightOrder() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":123,\"b\":\"Acting\",\"c\":\"1991\"}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874e3b70f00007107ec8c4b"); //{"a":123,"b":"Playing","c":"1991"}

        validator.validate(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareEmptyStringValue() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"b\"}");
        Response jsonResponse = get(" http://www.mocky.io/v2/5874e4090f00006107ec8c4c"); //{"a":""}

        validator.validate(jsonObject1, jsonResponse);

    }

    @Test
    public void testCompareFieldsWrongOrder() {
        JSONObject jsonObject1 = new JSONObject("{\"c\":\"1991\",\"a\":123,\"b\":\"Acting\"}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874e3b70f00007107ec8c4b"); //{"a":123,"b":"Playing","c":"1991"}

        validator.validate(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareThreeToTwoFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"Group\",\"b\":\"Acting\",\"c\":\"1991\"}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874f3790f0000e108ec8c8d"); //{"a":"Group","b":"Acting"}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareTwoToThreeFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"Group\",\"b\":\"Acting\"}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874e3b70f00007107ec8c4b"); //{"a":123,"b":"Playing","c":"1991"}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareArrayField() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"1\",\"b\":[\"5\"]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874e49e0f00007507ec8c50"); //{"a":"1","b":["qwerty"]}

        validator.validate(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareArrayIncorrectTypeField() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"1\",\"b\":[true]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874e49e0f00007507ec8c50"); //{"a":"1","b":["qwerty"]}

        validator.validate(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareArrayFewFields() {
        JSONObject jsonObject1 = new JSONObject("{\"b\":\"S@d.me\",\"c\":[1,3,\"qwe\"]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874e58d0f00009807ec8c53"); //{"b":"S@d.me","c":[566,"qw",12123]}

        validator.validate(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareArrayNullValueSchema() {
        JSONObject jsonObject1 = new JSONObject("{\"b\":\"S@d.me\",\"c\":null}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874e58d0f00009807ec8c53"); //{"b":"S@d.me","c":[566,"qw",12123]}

        validator.validate(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareNotArrayType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"Group\",\"b\":[1,3]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874f3790f0000e108ec8c8d"); //{"a":"Group","b":"Acting"}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareArrayFewArraysFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[[\"c\",\"y\"],[1,45]]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874f0fc0f00009108ec8c7f"); //{"a":[["c","y"],[1,45]]}

        validator.validate(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareArrayTwoToOne() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[[\"c\",\"y\"],[1,45]]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874f1150f00009908ec8c80"); //{"a":[["c","y"]]}

        validator.validate(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareEmptyArraySchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874f1680f00008908ec8c81"); //{"a":[1]}

        validator.validate(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareEmptyArrayArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[1]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874f17f0f0000a208ec8c83"); //{"a":[]}

        validator.validate(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectFieldType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"S@d.me\",\"b\":{\"c\":\"qwe\",\"d\":50}}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874e97e0f0000d707ec8c5e"); //{"a":"S@d.me","b":{"c":"qwe","d":88}}

        validator.validate(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectNullFieldSchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":null}}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874ea3a0f00000208ec8c63"); //{"a":{"b":50}}

        validator.validate(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectTypeNullValueSchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":null}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874ea3a0f00000208ec8c63"); //{"a":{"b":50}}

        validator.validate(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareEmptyArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":1}}}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874f4410f0000d008ec8c8f"); //{"a":{}}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectFieldIncorrectTypeInt() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":5}}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874f5010f0000ec08ec8c93");//{"a":{"b":"qwe"}}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectFieldIncorrectTypeString() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":\"qwe\"}}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874ea3a0f00000208ec8c63"); //{"a":{"b":50}}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectFieldIncorrectTypeBoolean() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":false}}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874f5010f0000ec08ec8c93");//{"a":{"b":"qwe"}}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectNullValueFieldArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":5}}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874f58f0f0000fd08ec8c9a"); //{"a":{"b":null}}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectIncorrectNameField() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"c\":\"qwe\"}}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874f5010f0000ec08ec8c93");//{"a":{"b":"qwe"}}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectTwoToOneFields() {
        JSONObject jsonObject1 = new JSONObject("{\"b\":{\"c\":\"586\",\"d\":50}}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874f7c50f00000c09ec8caa"); //{"b":{"c":""}}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectOneToTwoFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"S@d.me\",\"b\":{\"c\":\"qwe\"}}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874e97e0f0000d707ec8c5e"); //{"a":"S@d.me","b":{"c":"qwe","d":88}}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareNestedFieldArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":1}}}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874fa1f0f00003209ec8cb2"); //{"b":50}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareNotObjectType() {
        JSONObject jsonObject1 = new JSONObject("{\"b\":{\"c\":34344}}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874fa1f0f00003209ec8cb2"); //{"b":50}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectEmptySchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{}}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874ea3a0f00000208ec8c63"); //{"a":{"b":50}}

        validator.validate(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectInArrayFieldType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":\"qwerty\",\"d\":true}]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874ec680f00003208ec8c6e"); //{"a":[{"b":"qwerty","d":false}]}

        validator.validate(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectInArrayFieldsWrongOrder() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"d\":false, \"b\":\"qwerty\"}]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874ec680f00003208ec8c6e"); //{"a":[{"b":"qwerty","d":false}]}

        validator.validate(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectInArrayNullFieldSchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":null,\"d\":null}]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874ec680f00003208ec8c6e"); //{"a":[{"b":"qwerty","d":false}]}

        validator.validate(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectInArrayFieldNotObjectType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":\"asd\"}]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874ef340f00006608ec8c77"); //{"a":[5]}

        validator.validate(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectInArrayNullType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":null}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874f0190f00008008ec8c7c"); //{"a":[{"b":"asd"}]}

        validator.validate(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectInArrayFewObjectFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\"},{\"c\":\"a\"}]}");
        Response jsonResponse = get("http://www.mocky.io/v2/589845d411000037040389a7"); //{"a":[{"c":"y"},{"d":45}]}

        validator.validate(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareTwoObjectsInArrayWithDifferentFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\"},{\"d\":\"wd\"}]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5898474811000055040389b2"); //{"a":[{"c":"y"},{"d":"wd"}]}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareTwoObjectsInArrayWithDifferentTypes() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\"},{\"c\":45}]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5898478511000059040389b7"); //{"a":[{"c":"y"},{"c":45}]}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectInArrayEmptySchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{}]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874f0190f00008008ec8c7c"); //{"a":[{"b":"asd"}]}

        validator.validate(jsonObject1, jsonResponse);
    }


    @Test
    public void testCompareObjectInArrayTwoToOneObjects() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\"},{\"d\":45}]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874fa880f00003109ec8cb3"); //{"a":[{"c":"y"}]}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectInArrayOneToTwoObjects() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\"}]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874fae30f00005209ec8cb5"); //{"a":[{"c":"y"},{"d":45}]}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectInArrayEmptyArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":\"asd\"}]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874faff0f00005209ec8cb6");//{"a":[{}]}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectInArrayStringTypeArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":\"asd\"}]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874f2c40f0000c908ec8c87"); //{"a":"qwe"}
        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectInArrayIntTypeArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":5}]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874ddc80f0000d806ec8c3c"); //{"a":555}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectInArrayBooleanTypeArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":false}]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874e3620f00007207ec8c48"); //{"a":false}

        checkIsFailed(jsonObject1, jsonResponse);
    }


    @Test
    public void testCompareObjectInArrayIncorrectTypeString() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":\"asd\"}]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874fc7d0f00007609ec8cb9"); //{"a":[{"b":233}]}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test

    public void testCompareObjectInArrayIncorrectTypeInt() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":55}]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874f0190f00008008ec8c7c"); //{"a":[{"b":"asd"}]}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectInArrayIncorrectTypeBoolean() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":true}]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874f0190f00008008ec8c7c"); //{"a":[{"b":"asd"}]}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectInArrayNullValueArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\",\"d\":16}]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874fca50f00007a09ec8cba"); //{"a":[{"c":null,"d":null}]}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectInArrayIncorrectName() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"v\":\"y\",\"d\":true}]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874fcd50f00007609ec8cbb"); //{"a":[{"c":"y","d":true}]}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectInArrayOneToTwoFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\"}]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874fcd50f00007609ec8cbb");//{"a":[{"c":"y","d":true}]}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectInArrayTwoToOneFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\",\"d\":77}]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874fd700f00009c09ec8cbe"); //{"a":[{"c":"y"}]}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    @Test
    public void testCompareObjectInArrayNestedFieldArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":1}]}");
        Response jsonResponse = get("http://www.mocky.io/v2/5874fd8b0f00009e09ec8cbf"); //{"b":1}

        checkIsFailed(jsonObject1, jsonResponse);
    }

    private void checkIsFailed(JSONObject j1, Response j2) {
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
