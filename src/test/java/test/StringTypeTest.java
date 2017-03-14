package test;

import com.mbi.JsonValidator;
import org.json.JSONObject;
import org.testng.annotations.Test;

/**
 * Created by solg on 11.01.2017.
 */
public class StringTypeTest {

    private JsonValidator validator = new JsonValidator();

    @Test()
    public void testCompareCorrectType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":1}");
        String jsonString = "{\"a\":555}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareNullValueSchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":null}");
        String jsonString = "{\"a\":1}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareCorrectBooleanType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":true}");
        String jsonString = "{\"a\":false}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareIncorrectTypeInt() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":1}");
        String jsonString = "{\"a\":\"qwe\"}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareIncorrectTypeString() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"qwe\"}");
        String jsonString = "{\"a\":1}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareIncorrectBooleanType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":true}");
        String jsonString = "{\"a\":56}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareNullValueArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":1}");
        String jsonString = "{\"a\":null}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareIncorrectNameField() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":1}");
        String jsonString = "{\"b\":1}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareFieldsRightOrder() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":123,\"b\":\"Acting\",\"c\":\"1991\"}");
        String jsonString = "{\"a\":123,\"b\":\"Playing\",\"c\":\"1991\"}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareEmptyStringValue() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"b\"}");
        String jsonString = "{\"a\":\"\"}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareFieldsWrongOrder() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":123,\"b\":\"Acting\",\"c\":\"1991\"}");
        String jsonString = "{\"c\":\"1991\",\"a\":123,\"b\":\"Acting\"}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareThreeToTwoFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"Group\",\"b\":\"Acting\",\"c\":\"1991\"}");
        String jsonString = "{\"a\":\"Group\",\"b\":\"Acting\"}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareTwoToThreeFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"Group\",\"b\":\"Acting\"}");
        String jsonString = "{\"a\":\"Group\",\"b\":\"Acting\",\"c\":\"1991\"}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareArrayField() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"1\",\"b\":[\"5\"]}");
        String jsonString = "{\"a\":\"1\",\"b\":[\"qwerty\"]}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareArrayIncorrectTypeField() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"1\",\"b\":[\"5\"]}");
        String jsonString = "{\"a\":\"1\",\"b\":[true]}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareArrayFewFields() {
        JSONObject jsonObject1 = new JSONObject("{\"b\":\"S@d.me\",\"c\":[1,3,\"qwe\"]}");
        String jsonString = "{\"b\":\"S@d.me\",\"c\":[566,\"qw\",12123]}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareArrayNullValueSchema() {
        JSONObject jsonObject1 = new JSONObject("{\"id\":1,\"c\":null}");
        String jsonString = "{\"id\":1,\"c\":[1,3,5]}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareArrayFewArraysFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[[\"c\",\"y\"],[1,45]]}");
        String jsonString = "{\"a\":[[\"c\",\"y\"],[1,45]]}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareArrayTwoToOne() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[[\"c\",\"y\"],[1,45]]}");
        String jsonString = "{\"a\":[[\"c\",\"y\"]]}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareEmptyArraySchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[]}");
        String jsonString = "{\"a\":[1]}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareEmptyArrayArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[1]}");
        String jsonString = "{\"a\":[]}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareNotArrayType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"S@d.me\",\"b\":[1,3]}");
        String jsonString = "{\"a\":\"S@.me\",\"b\":\"hhh\"}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectFieldType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"S@d.me\",\"b\":{\"c\":\"586\",\"d\":50}}");
        String jsonString = "{\"a\":\"S@d.me\",\"b\":{\"c\":\"\",\"d\":88}}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareEmptyArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":1}}}");
        String jsonString = "{\"a\":{}}";

        checkIsFailed(jsonObject1, jsonString);
    }


    @Test
    public void testCompareObjectFieldIncorrectTypeInt() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":5}}");
        String jsonString = "{\"a\":{\"b\":\"a\"}}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectFieldIncorrectTypeString() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":\"qwe\"}}");
        String jsonString = "{\"a\":{\"b\":12}}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectFieldIncorrectTypeBoolean() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":false}}");
        String jsonString = "{\"a\":{\"b\":\"qwe\"}}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectNullFieldSchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":null}}");
        String jsonString = "{\"a\":{\"b\":50}}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectTypeNullValueSchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":null}");
        String jsonString = "{\"a\":{\"b\":50}}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectNullValueFieldArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":5}}");
        String jsonString = "{\"a\":{\"b\":null}}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectIncorrectNameField() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":\"a\"}}");
        String jsonString = "{\"a\":{\"c\":\"a\"}}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectTwoToOneFields() {
        JSONObject jsonObject1 = new JSONObject("{\"b\":{\"c\":\"586\",\"d\":50}}");
        String jsonString = "{\"b\":{\"c\":\"\"}}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectOneToTwoFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"c\":34344}}");
        String jsonString = "{\"a\":{\"b\":\"21321\",\"c\":50}}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareNestedFieldArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":1}}}");
        String jsonString = "{\"b\":1}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareNotObjectType() {
        JSONObject jsonObject1 = new JSONObject("{\"b\":{\"c\":34344}}");
        String jsonString = "{\"b\":50}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectEmptySchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{}}");
        String jsonString = "{\"a\":{\"b\":\"asd\"}}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectInArrayFieldType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":\"qwerty\",\"d\":true}]}");
        String jsonString = "{\"a\":[{\"b\":\"qwerty\",\"d\":false}]}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectInArrayFieldsWrongOrder() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":\"qwerty\",\"d\":16}]}");
        String jsonString = "{\"a\":[{\"d\":16, \"b\":\"qwerty\"}]}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectInArrayNullFieldSchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":null,\"d\":null}]}");
        String jsonString = "{\"a\":[{\"c\":\"y\",\"d\":16}]}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectInArrayFieldNotObjectType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":\"asd\"}]}");
        String jsonString = "{\"a\":[5]}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectInArrayNullType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":null}");
        String jsonString = "{\"a\":[{\"b\":\"asd\"}]}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectInArrayFewObjectFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\"},{\"c\":\"wd\"}]}");
        String jsonString = "{\"a\":[{\"c\":\"y\"},{\"c\":\"xxx45\"}]}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareTwoObjectsInArrayWithDifferentFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\"},{\"d\":\"wd\"}]}");
        String jsonString = "{\"a\":[{\"c\":\"y\"},{\"d\":\"xxx45\"}]}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareTwoObjectsInArrayWithDifferentTypes() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\"},{\"c\":\"wd\"}]}");
        String jsonString = "{\"a\":[{\"c\":\"y\"},{\"c\":45}]}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectInArrayEmptySchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{}]}");
        String jsonString = "{\"a\":[{\"b\":\"asd\"}]}";

        validator.validate(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectInArrayTwoToOneObjects() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\"},{\"d\":45}]}");
        String jsonString = "{\"a\":[{\"c\":\"y\"}]}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectInArrayOneToTwoObjects() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\"}]}");
        String jsonString = "{\"a\":[{\"c\":\"y\"},{\"d\":45}]}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectInArrayEmptyArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":\"asd\"}]}");
        String jsonString = "{\"a\":[{}]}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectInArrayStringTypeArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":\"asd\"}]}");
        String jsonString = "{\"a\":\"qwe\"}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectInArrayIntTypeArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":5}]}");
        String jsonString = "{\"a\":5}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectInArrayBooleanTypeArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":false}]}");
        String jsonString = "{\"a\":false}";

        checkIsFailed(jsonObject1, jsonString);
    }


    @Test
    public void testCompareObjectInArrayIncorrectTypeString() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":\"asd\"}]}");
        String jsonString = "{\"a\":[{\"b\":233}]}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test

    public void testCompareObjectInArrayIncorrectTypeInt() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":55}]}");
        String jsonString = "{\"a\":[{\"b\":\"asd\"}]}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectInArrayIncorrectTypeBoolean() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":true}]}");
        String jsonString = "{\"a\":[{\"b\":\"asd\"}]}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectInArrayNullValueArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\",\"d\":16}]}");
        String jsonString = "{\"a\":[{\"c\":null,\"d\":null}]}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectInArrayIncorrectName() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\",\"d\":true}]}");
        String jsonString = "{\"a\":[{\"v\":\"y\",\"d\":true}]}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectInArrayOneToTwoFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\"}]}");
        String jsonString = "{\"a\":[{\"c\":\"y\",\"d\":77}]}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectInArrayTwoToOneFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\",\"d\":77}]}");
        String jsonString = "{\"a\":[{\"c\":\"y\"}]}";

        checkIsFailed(jsonObject1, jsonString);
    }

    @Test
    public void testCompareObjectInArrayNestedFieldArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":1}]}");
        String jsonString = "{\"b\":1}";

        checkIsFailed(jsonObject1, jsonString);
    }

    private void checkIsFailed(JSONObject j1, String j2) {
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
