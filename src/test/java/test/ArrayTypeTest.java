package test;

import com.mbi.JsonValidator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

/**
 * Created by solg on 11.01.2017.
 */
public class ArrayTypeTest {

    private JsonValidator validator = new JsonValidator();


    @Test
    public void testCompareCorrectType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":1}");
        JSONArray jsonArray = new JSONArray("[{\"a\":555}]");

        validator.validate(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareNullValueSchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":null}");
        JSONArray jsonArray = new JSONArray("[{\"a\":1}]");

        validator.validate(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareNullValueArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":1}");
        JSONArray jsonArray = new JSONArray("[{\"a\":null}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareCorrectBooleanType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":true}");
        JSONArray jsonArray = new JSONArray("[{\"a\":false}]");

        validator.validate(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareIncorrectTypeInt() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":1}");
        JSONArray jsonArray = new JSONArray("[{\"a\":\"qwe\"}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareIncorrectTypeString() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"qwe\"}");
        JSONArray jsonArray = new JSONArray("[{\"a\":4}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareIncorrectBooleanType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":true}");
        JSONArray jsonArray = new JSONArray("[{\"a\":56}]");

        checkIsFailed(jsonObject1, jsonArray);
    }


    @Test
    public void testCompareIncorrectNameField() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":1}");
        JSONArray jsonArray = new JSONArray("[{\"b\":1}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareFieldsRightOrder() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":123,\"b\":\"Acting\",\"c\":\"1991\"}");
        JSONArray jsonArray = new JSONArray("[{\"a\":123,\"b\":\"Playing\",\"c\":\"1991\"}]");

        validator.validate(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareEmptyStringValue() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"b\"}");
        JSONArray jsonArray = new JSONArray("[{\"a\":\"\"}]");

        validator.validate(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareFieldsWrongOrder() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":123,\"b\":\"Acting\",\"c\":\"1991\"}");
        JSONArray jsonArray = new JSONArray("[{\"c\":\"1991\",\"a\":123,\"b\":\"Acting\"}]");

        validator.validate(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareThreeToTwoFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"Group\",\"b\":\"Acting\",\"c\":\"1991\"}");
        JSONArray jsonArray = new JSONArray("[{\"a\":\"Group\",\"b\":\"Acting\"}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareTwoToThreeFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"Group\",\"b\":\"Acting\"}");
        JSONArray jsonArray = new JSONArray("[{\"a\":\"Group\",\"b\":\"Acting\",\"c\":\"1991\"}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareArrayField() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"1\",\"b\":[\"5\"]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":\"1\",\"b\":[\"qwerty\"]}]");

        validator.validate(jsonObject1, jsonArray);
    }

    @Test////////?
    public void testCompareArrayIncorrectTypeField() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"1\",\"b\":[\"5\"]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":\"1\",\"b\":[true]}]");

        validator.validate(jsonObject1, jsonArray);
    }

    @Test //////?
    public void testCompareArrayFewFields() {
        JSONObject jsonObject1 = new JSONObject("{\"b\":\"S@d.me\",\"c\":[1,3,\"qwe\"]}");
        JSONArray jsonArray = new JSONArray("[{\"b\":\"S@d.me\",\"c\":[566,\"qw\",12123]}]");

        validator.validate(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareArrayNullValueSchema() {
        JSONObject jsonObject1 = new JSONObject("{\"id\":1,\"c\":null}");
        JSONArray jsonArray = new JSONArray("[{\"id\":1,\"c\":[1,3,5]}]");

        validator.validate(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareArrayFewArraysFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[[\"c\",\"y\"],[1,45]]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":[[\"c\",\"y\"],[1,45]]}]");

        validator.validate(jsonObject1, jsonArray);
    }

    @Test////////////?
    public void testCompareArrayTwoToOne() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[[\"c\",\"y\"],[1,45]]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":[[\"c\",\"y\"]]}]");

        validator.validate(jsonObject1, jsonArray);
    }

    @Test   /////////////////?
    public void testCompareEmptyArraySchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":[1]}]");

        validator.validate(jsonObject1, jsonArray);
    }

    @Test   /////////////////?
    public void testCompareEmptyArrayArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[1]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":[]}]");

        validator.validate(jsonObject1, jsonArray);
    }


    @Test
    public void testCompareNotArrayType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"S@d.me\",\"b\":[1,3]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":\"S@.me\",\"b\":\"hhh\"}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectFieldType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"S@d.me\",\"b\":{\"c\":\"586\",\"d\":50}}");
        JSONArray jsonArray = new JSONArray("[{\"a\":\"S@d.me\",\"b\":{\"c\":\"\",\"d\":88}}]");

        validator.validate(jsonObject1, jsonArray);
    }

    @Test ////////////?
    public void testCompareEmptyArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":1}}}");
        JSONArray jsonArray = new JSONArray("[{\"a\":{}}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectFieldIncorrectTypeInt() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":5}}");
        JSONArray jsonArray = new JSONArray("[{\"a\":{\"b\":\"a\"}}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectFieldIncorrectTypeString() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":\"a\"}}");
        JSONArray jsonArray = new JSONArray("[{\"a\":{\"b\":2}}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectFieldIncorrectTypeBoolean() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":false}}");
        JSONArray jsonArray = new JSONArray("[{\"a\":{\"b\":\"a\"}}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectNullFieldSchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":null}}");
        JSONArray jsonArray = new JSONArray("[{\"a\":{\"b\":50}}]");

        validator.validate(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectTypeNullValueSchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":null}");
        JSONArray jsonArray = new JSONArray("[{\"a\":{\"b\":50}}]");

        validator.validate(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectNullValueFieldArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":5}}");
        JSONArray jsonArray = new JSONArray("[{\"a\":{\"b\":null}}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectIncorrectNameField() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":\"a\"}}");
        JSONArray jsonArray = new JSONArray("[{\"a\":{\"c\":\"a\"}}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectThreeToTwoFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"S@d.me\",\"b\":{\"c\":\"586\",\"d\":50}}");
        JSONArray jsonArray = new JSONArray("[{\"a\":\"S@d.me\",\"b\":{\"c\":\"\"}}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectTwoToThreeFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"c\":34344}}");
        JSONArray jsonArray = new JSONArray("[{\"a\":{\"b\":\"21321\",\"c\":50}}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test ////////////?
    public void testCompareNestedFieldArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":1}}}");
        JSONArray jsonArray = new JSONArray("[{\"b\":1}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareNotObjectType() {
        JSONObject jsonObject1 = new JSONObject("{\"b\":{\"c\":34344}}");
        JSONArray jsonArray = new JSONArray("[{\"b\":50}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test/////////?
    public void testCompareObjectEmptySchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{}}");
        JSONArray jsonArray = new JSONArray("[{\"a\":{\"b\":\"asd\"}}]");

        validator.validate(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectInArrayFieldType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":\"qwerty\",\"d\":true}]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":[{\"b\":\"qwerty\",\"d\":false}]}]");

        validator.validate(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectInArrayFieldsWrongOrder() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":\"qwerty\",\"d\":16}]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":[{\"d\":16, \"b\":\"qwerty\"}]}]");

        validator.validate(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectInArrayNullFieldSchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":null,\"d\":null}]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":[{\"c\":\"y\",\"d\":16}]}]");

        validator.validate(jsonObject1, jsonArray);
    }

    @Test//////?
    public void testCompareObjectInArrayFieldNotObjectType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":\"asd\"}]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":[5]}]");

        validator.validate(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectInArrayNullType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":null}");
        JSONArray jsonArray = new JSONArray("[{\"a\":[{\"b\":\"asd\"}]}]");

        validator.validate(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectInArrayFewObjectFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\"},{\"d\":45}]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":[{\"c\":\"y\"},{\"d\":16}]}]");

        validator.validate(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectInArrayEmptySchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{}]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":[{\"b\":\"asd\"}]}]");

        validator.validate(jsonObject1, jsonArray);
    }


    @Test
    public void testCompareObjectInArrayTwoToOneObjects() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\"},{\"d\":45}]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":[{\"c\":\"y\"}]}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectInArrayOneToTwoObjects() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\"}]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":[{\"c\":\"y\"},{\"d\":45}]}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test/////////?
    public void testCompareObjectInArrayEmptyArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":\"asd\"}]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":[{}]}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectInArrayStringTypeArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":\"asd\"}]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":\"qwe\"}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectInArrayIntTypeArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":5}]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":5}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectInArrayBooleanTypeArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":false}]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":false}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectInArrayIncorrectTypeString() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":\"asd\"}]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":[{\"b\":233}]}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectInArrayIncorrectTypeInt() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":55}]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":[{\"b\":\"asd\"}]}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectInArrayIncorrectTypeBoolean() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":true}]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":[{\"b\":\"asd\"}]}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectInArrayNullValueArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\",\"d\":16}]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":[{\"c\":null,\"d\":null}]}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectInArrayIncorrectName() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\",\"d\":true}]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":[{\"v\":\"y\",\"d\":true}]}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectInArrayOneToTwoFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\"}]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":[{\"c\":\"y\",\"d\":77}]}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test
    public void testCompareObjectInArrayTwoToOneFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\",\"d\":77}]}");
        JSONArray jsonArray = new JSONArray("[{\"a\":[{\"c\":\"y\"}]}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    @Test ////////////?
    public void testCompareObjectInArrayNestedFieldArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":1}]}");
        JSONArray jsonArray = new JSONArray("[{\"b\":1}]");

        checkIsFailed(jsonObject1, jsonArray);
    }

    private void checkIsFailed(JSONObject j1, JSONArray j2) {

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


