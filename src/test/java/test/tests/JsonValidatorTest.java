package test.tests;

import org.json.JSONObject;
import org.testng.annotations.Test;

import static test.serializer.JsonDeserializer.getJsonFromFile;

public class JsonValidatorTest extends TestHelper {

    private final JSONObject schema = getJsonFromFile("/schemas/schema.json");

    @Test(dataProvider = "SuccessValidation")
    public void testValidationSuccess(Object o) {
        checkSuccess(schema, o);
    }

    @Test(dataProvider = "InvalidType")
    public void testValidationWithInvalidTypeFailed(Object o) {
        checkFail(schema, o);
    }

    @Test(dataProvider = "NullPropertySuccess")
    public void testValidationWithNullOrStringPropertySuccess(Object o) {
        checkSuccess(schema, o);
    }

    @Test(dataProvider = "NullPropertyFailed")
    public void testValidationWithNullOrStringPropertyFailed(Object o) {
        checkFail(schema, o);
    }

    @Test(dataProvider = "EmptyObject")
    public void testValidationEmptyObjectSuccess(Object o) {
        checkSuccess(schema, o);
    }

    @Test(dataProvider = "EmptyArray")
    public void testValidationEmptyArraySuccess(Object o) {
        checkSuccess(schema, o);
    }

    @Test(dataProvider = "ValidateArray")
    public void testValidateArray(Object o) {
        checkSuccess(schema, o);
    }

    @Test(dataProvider = "ExtraField")
    public void testValidateExtraFieldFailed(Object o) {
        checkFail(schema, o);
    }

    @Test(dataProvider = "ExtraObject")
    public void testValidateExtraObjectFailed(Object o) {
        checkFail(schema, o);
    }

    @Test(dataProvider = "WithoutField")
    public void testValidateWithoutFieldFailed(Object o) {
        checkFail(schema, o);
    }

    @Test(dataProvider = "InvalidValue")
    public void testValidateWithInvalidValueFailed(Object o) {
        checkFail(schema, o);
    }

    @Test(dataProvider = "EmptyArrayFailed")
    public void testValidateWithEmptyArrayFailed(Object o) {
        checkFail(schema, o);
    }

    @Test(dataProvider = "EmptyObjectFailed")
    public void testValidateEmptyObjectFailed(Object o) {
        checkFail(schema, o);
    }

    @Test(dataProvider = "ExtraFieldInNestedObject")
    public void testValidateExtraField(Object o) {
        checkFail(schema, o);
    }

    @Test(dataProvider = "ObjectWithoutField")
    public void testValidateObjectWithoutFieldFailed(Object o) {
        checkFail(schema, o);
    }

    @Test(dataProvider = "ObjectWithInvalidType")
    public void testValidateObjectWithInvalidType(Object o) {
        checkFail(schema, o);
    }

    @Test(dataProvider = "ObjectWithoutNestedObject")
    public void testValidateObjectWithoutNestedObjectFailed(Object o) {
        checkFail(schema, o);
    }

    @Test(dataProvider = "NestedObjectExtraFieldFailed")
    public void testValidateObjectNestedObjectExtraFieldFailed(Object o) {
        checkFail(schema, o);
    }

    @Test(dataProvider = "NestedObjectWithoutFieldFailed")
    public void testValidateObjectNestedObjectWithoutFieldFailed(Object o) {
        checkFail(schema, o);
    }

    @Test(dataProvider = "ArrayHasInvalidObject")
    public void testJsonArrayHasInvalidObject(Object o) {
        checkFail(schema, o);
    }

    @Test
    public void tesEmptySchemaFailed() {
        JSONObject j1 = new JSONObject("{\"a\":1}");
        JSONObject j2 = new JSONObject("{\"a\":1}");

        checkFail(j1, j2);
    }
}