package test.tests;

import com.mbi.JsonValidator;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.testng.Assert.*;
import static test.serializer.JsonDeserializer.getJsonFromFile;

public class JsonValidatorTest extends TestHelper {

    private final JSONObject schemaJsonObject = getJsonFromFile("/schemas/schema_json_object.json");
    private final JSONObject schemaJsonArray = getJsonFromFile("/schemas/schema_json_array.json");
    private final JsonValidator validator = new JsonValidator();

    @Test(dataProvider = "SuccessValidation", dataProviderClass = DataProviders.class)
    public void testValidationSuccess(Object o) {
        checkSuccess(schemaJsonObject, o);
    }

    @Test(dataProvider = "InvalidType", dataProviderClass = DataProviders.class)
    public void testValidationWithInvalidTypeFailed(Object o) {
        checkFail(schemaJsonObject, o);
    }

    @Test(dataProvider = "NullPropertySuccess", dataProviderClass = DataProviders.class)
    public void testValidationWithNullOrStringPropertySuccess(Object o) {
        checkSuccess(schemaJsonObject, o);
    }

    @Test(dataProvider = "NullPropertyFailed", dataProviderClass = DataProviders.class)
    public void testValidationWithNullOrStringPropertyFailed(Object o) {
        checkFail(schemaJsonObject, o);
    }

    @Test(dataProvider = "EmptyObject", dataProviderClass = DataProviders.class)
    public void testValidationEmptyObjectSuccess(Object o) {
        checkSuccess(schemaJsonObject, o);
    }

    @Test(dataProvider = "EmptyArray", dataProviderClass = DataProviders.class)
    public void testValidationEmptyArraySuccess(Object o) {
        checkSuccess(schemaJsonArray, o);
    }

    @Test(dataProvider = "ValidateArray", dataProviderClass = DataProviders.class)
    public void testValidateArray(Object o) {
        checkSuccess(schemaJsonArray, o);
    }

    @Test(dataProvider = "ExtraField", dataProviderClass = DataProviders.class)
    public void testValidateExtraFieldFailed(Object o) {
        checkFail(schemaJsonObject, o);
    }

    @Test(dataProvider = "ExtraObject", dataProviderClass = DataProviders.class)
    public void testValidateExtraObjectFailed(Object o) {
        checkFail(schemaJsonObject, o);
    }

    @Test(dataProvider = "WithoutField", dataProviderClass = DataProviders.class)
    public void testValidateWithoutFieldFailed(Object o) {
        checkFail(schemaJsonObject, o);
    }

    @Test(dataProvider = "InvalidValue", dataProviderClass = DataProviders.class)
    public void testValidateWithInvalidValueFailed(Object o) {
        checkFail(schemaJsonObject, o);
    }

    @Test(dataProvider = "EmptyArrayFailed", dataProviderClass = DataProviders.class)
    public void testValidateWithEmptyArrayFailed(Object o) {
        checkFail(schemaJsonObject, o);
    }

    @Test(dataProvider = "EmptyObjectFailed", dataProviderClass = DataProviders.class)
    public void testValidateEmptyObjectFailed(Object o) {
        checkFail(schemaJsonObject, o);
    }

    @Test(dataProvider = "ExtraFieldInNestedObject", dataProviderClass = DataProviders.class)
    public void testValidateExtraField(Object o) {
        checkFail(schemaJsonObject, o);
    }

    @Test(dataProvider = "ObjectWithoutField", dataProviderClass = DataProviders.class)
    public void testValidateObjectWithoutFieldFailed(Object o) {
        checkFail(schemaJsonObject, o);
    }

    @Test(dataProvider = "ObjectWithInvalidType", dataProviderClass = DataProviders.class)
    public void testValidateObjectWithInvalidType(Object o) {
        checkFail(schemaJsonObject, o);
    }

    @Test(dataProvider = "ObjectWithoutNestedObject", dataProviderClass = DataProviders.class)
    public void testValidateObjectWithoutNestedObjectFailed(Object o) {
        checkFail(schemaJsonObject, o);
    }

    @Test(dataProvider = "NestedObjectExtraFieldFailed", dataProviderClass = DataProviders.class)
    public void testValidateObjectNestedObjectExtraFieldFailed(Object o) {
        checkFail(schemaJsonObject, o);
    }

    @Test(dataProvider = "NestedObjectWithoutFieldFailed", dataProviderClass = DataProviders.class)
    public void testValidateObjectNestedObjectWithoutFieldFailed(Object o) {
        checkFail(schemaJsonObject, o);
    }

    @Test(dataProvider = "ArrayHasInvalidObject", dataProviderClass = DataProviders.class)
    public void testJsonArrayHasInvalidObject(Object o) {
        checkFail(schemaJsonObject, o);
    }

    @Test
    public void testEmptySchemaFailed() {
        JSONObject j1 = new JSONObject();
        JSONObject j2 = new JSONObject("{\"a\":1}");

        checkFail(j1, j2);
    }

    @Test
    public void testInvalidSchemaIsTreatedAsPermissive() {
        JSONObject j1 = new JSONObject("{\"b\":[]}");
        JSONObject j2 = new JSONObject("{\"a\":1}");

        checkSuccess(j1, j2);
    }

    @Test(dataProvider = "ExtraField", dataProviderClass = DataProviders.class)
    public void testNullSchema(Object o) {
        assertThrows(AssertionError.class, () -> checkSuccess(null, o));
    }

    @Test
    public void testNullJson() {
        var schema = new JSONObject("{\"a\":1}");

        assertThrows(NullPointerException.class, () -> validator.validate(schema, (JSONObject) null));
        assertThrows(NullPointerException.class, () -> validator.validate(schema, (JSONArray) null));
        assertThrows(NullPointerException.class, () -> validator.validate(schema, (Response) null));
        assertThrows(NullPointerException.class, () -> validator.validate(schema, (String) null));
    }

    @Test(dataProvider = "InvalidJson", dataProviderClass = DataProviders.class)
    public void testInvalidJsonFailed(Object o) {
        checkFail(schemaJsonObject, o);
    }

    @Test
    public void testLargeArrayValidation() {
        var arr = new JSONArray();

        for (int i = 0; i < 1000; i++) {
            var obj = new JSONObject()
                    .put("a", 1)
                    .put("b", JSONObject.NULL)
                    .put("c", "1")
                    .put("d", new JSONArray())
                    .put("e", new JSONObject())
                    .put("f", new JSONArray().put(new JSONObject().put("aa", 1)))
                    .put("g", new JSONObject()
                            .put("aa", 1)
                            .put("bb", new JSONObject().put("aaa", 1)));

            arr.put(obj);
        }

        validator.validate(schemaJsonArray, arr);
    }

    @Test
    public void testInvalidJsonInFile() throws IOException {
        var schema = getJsonFromFile("/schemas/schema_json_object.json");
        var brokenJson = new String(Files.readAllBytes(Paths.get("src/test/resources/suites/validation_test.xml")));

        try {
            validator.validate(schema, brokenJson);
        } catch (JSONException e) {
            assertTrue(e.getMessage().contains("Invalid json:"));
        }
    }

    @Test
    public void testAllOfSchemaSuccess() {
        var schema = getJsonFromFile("/schemas/all_of_schema.json");
        var json = new JSONObject().put("a", "test").put("b", 123);

        checkSuccess(schema, json);
    }

    @Test
    public void testAnyOfSchemaSuccess() {
        var schema = getJsonFromFile("/schemas/any_of_schema.json");
        var json = new JSONObject().put("a", "value");

        checkSuccess(schema, json);
    }

    @Test
    public void testAnyOfSchemaAlternativeSuccess() {
        var schema = getJsonFromFile("/schemas/any_of_schema.json");
        var json = new JSONObject().put("b", 123);

        checkSuccess(schema, json);
    }

    @Test
    public void testNotKeywordFailed() {
        var schema = getJsonFromFile("/schemas/not_schema.json");
        var json = new JSONObject().put("forbidden", true);

        checkFail(schema, json);
    }

    @Test
    public void testPatternPropertiesSuccess() {
        var schema = getJsonFromFile("/schemas/pattern_properties_schema.json");
        var json = new JSONObject()
                .put("fooName", "value")
                .put("barAge", 10);

        checkSuccess(schema, json);
    }

    @Test
    public void testPatternPropertiesFailed() {
        var schema = getJsonFromFile("/schemas/pattern_properties_schema.json");
        var json = new JSONObject()
                .put("fooName", 123);

        checkFail(schema, json);
    }
}