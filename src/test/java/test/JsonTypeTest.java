package test;

import com.mbi.JsonValidator;
import org.json.JSONObject;
import org.testng.annotations.Test;

/**
 * Created by solg on 11.01.2017.
 */
public class JsonTypeTest {


    private JsonValidator validator = new JsonValidator();


    @Test()
    public void testCompareCorrectType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":1}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":555}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareNullValueSchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":null}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":1}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareCorrectBooleanType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":true}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":false}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareIncorrectTypeInt() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":1}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":\"qwe\"}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareIncorrectTypeString() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"qwe\"}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":1}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareIncorrectBooleanType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":true}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":56}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareNullValueArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":1}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":null}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareIncorrectNameField() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":1}");
        JSONObject jsonObject2 = new JSONObject("{\"b\":1}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareFieldsRightOrder() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":123,\"b\":\"Acting\",\"c\":\"1991\"}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":123,\"b\":\"Playing\",\"c\":\"1991\"}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareEmptyStringValue() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"b\"}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":\"\"}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareFieldsWrongOrder() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":123,\"b\":\"Acting\",\"c\":\"1991\"}");
        JSONObject jsonObject2 = new JSONObject("{\"c\":\"1991\",\"a\":123,\"b\":\"Acting\"}");

        validator.validate(jsonObject1, jsonObject2);
        validator.validate(jsonObject2, jsonObject1);
    }

    @Test
    public void testCompareThreeToTwoFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"Group\",\"b\":\"Acting\",\"c\":\"1991\"}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":\"Group\",\"b\":\"Acting\"}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareTwoToThreeFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"Group\",\"b\":\"Acting\"}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":\"Group\",\"b\":\"Acting\",\"c\":\"1991\"}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareArrayField() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"1\",\"b\":[\"5\"]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":\"1\",\"b\":[\"qwerty\"]}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test ////////////?
    public void testCompareArrayIncorrectTypeField() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"1\",\"b\":[\"5\"]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":\"1\",\"b\":[true]}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test /////////?
    public void testCompareArrayFewFields() {
        JSONObject jsonObject1 = new JSONObject("{\"b\":\"S@d.me\",\"c\":[1,3,\"qwe\"]}");
        JSONObject jsonObject2 = new JSONObject("{\"b\":\"S@d.me\",\"c\":[566,\"qw\",12123]}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareArrayNullValueSchema() {
        JSONObject jsonObject1 = new JSONObject("{\"id\":1,\"c\":null}");
        JSONObject jsonObject2 = new JSONObject("{\"id\":1,\"c\":[1,3,5]}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareArrayFewArraysFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[[\"c\",\"y\"],[1,45]]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":[[\"c\",\"y\"],[1,45]]}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test////////////?
    public void testCompareArrayTwoToOne() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[[\"c\",\"y\"],[1,45]]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":[[\"c\",\"y\"]]}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test   /////////////////?
    public void testCompareEmptyArraySchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":[1]}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test   /////////////////?
    public void testCompareEmptyArrayArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[1]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":[]}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareNotArrayType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"S@d.me\",\"b\":[1,3]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":\"S@.me\",\"b\":\"hhh\"}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareObjectFieldType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":\"S@d.me\",\"b\":{\"c\":\"586\",\"d\":50}}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":\"S@d.me\",\"b\":{\"c\":\"\",\"d\":88}}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test ////////////?
    public void testCompareEmptyArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":1}}}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":{}}");

        checkIsFailed(jsonObject1, jsonObject2);
    }


    @Test
    public void testCompareObjectFieldIncorrectTypeInt() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":5}}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":{\"b\":\"a\"}}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareObjectFieldIncorrectTypeString() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":\"qwe\"}}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":{\"b\":12}}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareObjectFieldIncorrectTypeBoolean() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":false}}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":{\"b\":\"qwe\"}}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareObjectNullFieldSchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":null}}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":{\"b\":50}}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareObjectTypeNullValueSchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":null}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":{\"b\":50}}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareObjectNullValueFieldArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":5}}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":{\"b\":null}}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareObjectIncorrectNameField() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":\"a\"}}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":{\"c\":\"a\"}}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareObjectTwoToOneFields() {
        JSONObject jsonObject1 = new JSONObject("{\"b\":{\"c\":\"586\",\"d\":50}}");
        JSONObject jsonObject2 = new JSONObject("{\"b\":{\"c\":\"\"}}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareObjectOneToTwoFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"c\":34344}}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":{\"b\":\"21321\",\"c\":50}}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test ////////////?
    public void testCompareNestedFieldArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{\"b\":1}}}");
        JSONObject jsonObject2 = new JSONObject("{\"b\":1}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareNotObjectType() {
        JSONObject jsonObject1 = new JSONObject("{\"b\":{\"c\":34344}}");
        JSONObject jsonObject2 = new JSONObject("{\"b\":50}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test/////////?
    public void testCompareObjectEmptySchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":{}}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":{\"b\":\"asd\"}}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareObjectInArrayFieldType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":\"qwerty\",\"d\":true}]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":[{\"b\":\"qwerty\",\"d\":false}]}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareObjectInArrayFieldsWrongOrder() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":\"qwerty\",\"d\":16}]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":[{\"d\":16, \"b\":\"qwerty\"}]}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareObjectInArrayNullFieldSchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":null,\"d\":null}]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":[{\"c\":\"y\",\"d\":16}]}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test//////////////?
    public void testCompareObjectInArrayFieldNotObjectType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":\"asd\"}]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":[5]}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareObjectInArrayNullType() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":null}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":[{\"b\":\"asd\"}]}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareObjectInArrayFewObjectFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\"},{\"d\":45}]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":[{\"c\":\"y\"},{\"d\":45}]}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test/////////?
    public void testCompareObjectInArrayEmptySchema() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{}]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":[{\"b\":\"asd\"}]}");

        validator.validate(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareObjectInArrayTwoToOneObjects() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\"},{\"d\":45}]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":[{\"c\":\"y\"}]}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareObjectInArrayOneToTwoObjects() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\"}]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":[{\"c\":\"y\"},{\"d\":45}]}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test/////////?
    public void testCompareObjectInArrayEmptyArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":\"asd\"}]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":[{}]}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareObjectInArrayStringTypeArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":\"asd\"}]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":\"qwe\"}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareObjectInArrayIntTypeArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":5}]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":5}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareObjectInArrayBooleanTypeArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":false}]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":false}");

        checkIsFailed(jsonObject1, jsonObject2);
    }


    @Test
    public void testCompareObjectInArrayIncorrectTypeString() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":\"asd\"}]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":[{\"b\":233}]}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test

    public void testCompareObjectInArrayIncorrectTypeInt() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":55}]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":[{\"b\":\"asd\"}]}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareObjectInArrayIncorrectTypeBoolean() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":true}]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":[{\"b\":\"asd\"}]}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareObjectInArrayNullValueArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\",\"d\":16}]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":[{\"c\":null,\"d\":null}]}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareObjectInArrayIncorrectName() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\",\"d\":true}]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":[{\"v\":\"y\",\"d\":true}]}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareObjectInArrayOneToTwoFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\"}]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":[{\"c\":\"y\",\"d\":77}]}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test
    public void testCompareObjectInArrayTwoToOneFields() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"c\":\"y\",\"d\":77}]}");
        JSONObject jsonObject2 = new JSONObject("{\"a\":[{\"c\":\"y\"}]}");

        checkIsFailed(jsonObject1, jsonObject2);
    }

    @Test ////////////?
    public void testCompareObjectInArrayNestedFieldArg2() {
        JSONObject jsonObject1 = new JSONObject("{\"a\":[{\"b\":1}]}");
        JSONObject jsonObject2 = new JSONObject("{\"b\":1}");

        checkIsFailed(jsonObject1, jsonObject2);
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


