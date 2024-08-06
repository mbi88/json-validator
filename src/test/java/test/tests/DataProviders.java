package test.tests;


import org.testng.annotations.DataProvider;

import static io.restassured.RestAssured.get;
import static test.serializer.JsonDeserializer.getJsonArrayFromFile;
import static test.serializer.JsonDeserializer.getJsonFromFile;

public class DataProviders {

    @DataProvider(name = "SuccessValidation")
    static Object[][] getSuccessValidationData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/success_validation_jo.json")},
                {get("https://api.npoint.io/dfce6fd1a49877114cda")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "InvalidType")
    static Object[][] getInvalidTypeData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/invalid_type_jo.json")},
                {getJsonArrayFromFile("/jsons/invalid_type_ja.json")},
                {get("https://api.npoint.io/148d863c8b81b191e779")},
                {"""
                    {"a":"1","b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "NullPropertySuccess")
    static Object[][] getNullPropertySuccessData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/null_property_success_jo.json")},
                {get("https://api.npoint.io/4ae374e7e33bc8082920")},
                {"""
                    {"a":1,"b":"a","c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "NullPropertyFailed")
    static Object[][] getNullPropertyFailedData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/null_property_failed_jo.json")},
                {getJsonArrayFromFile("/jsons/null_property_failed_ja.json")},
                {get("https://api.npoint.io/cf4ca946c8dc328f5a7c")},
                {"""
                    {"a":1,"b":1,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "EmptyObject")
    static Object[][] getEmptyObjectData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/empty_object_jo.json")},
                {get("https://api.npoint.io/e28d43c94b98b15ac9a2")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{"a":1},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "EmptyArray")
    static Object[][] getEmptyArrayData() {
        return new Object[][]{
                {getJsonArrayFromFile("/jsons/empty_array_ja.json")},
                {get("https://api.npoint.io/df5e71a711f0b29b95a3")},
                {"""
                    [{"a":1,"b":null,"c":"1","d":[1],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1}}},{"a":1,"b":null,"c":"1","d":[1],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1}}}]"""}
        };
    }

    @DataProvider(name = "ValidateArray")
    static Object[][] getValidateArrayData() {
        return new Object[][]{
                {getJsonArrayFromFile("/jsons/validate_array_ja.json")},
                {get("https://api.npoint.io/c94a5674e5623fb08da8")},
                {"""
                    [{"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1}],"g":{"aa":1,"bb":{"aaa": 1}}}, {"a": 2, "b": null, "c": "1", "d": [], "e": {}, "f": [{"aa": 1}], "g": {"aa": 1, "bb": {"aaa": 1}}}]"""}
        };
    }

    @DataProvider(name = "ExtraField")
    static Object[][] getExtraFieldData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/extra_field_jo.json")},
                {getJsonArrayFromFile("/jsons/extra_field_ja.json")},
                {get("https://api.npoint.io/b6314d7b65a0dffbe811")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1,"a":1}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "ExtraObject")
    static Object[][] getExtraObjectData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/extra_object_jo.json")},
                {getJsonArrayFromFile("/jsons/extra_object_ja.json")},
                {get("https://api.npoint.io/2c00dc55fd99a4b28ce4")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"bb":1}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "WithoutField")
    static Object[][] getWithoutFieldData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/wo_field_jo.json")},
                {getJsonArrayFromFile("/jsons/wo_field_ja.json")},
                {get("https://api.npoint.io/76b78c57196508860f39")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"bb":1}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "InvalidValue")
    static Object[][] getInvalidValueData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/invalid_value_jo.json")},
                {getJsonArrayFromFile("/jsons/invalid_value_ja.json")},
                {get("https://api.npoint.io/9c75691cc117e1d4da52")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":"1"}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "EmptyArrayFailed")
    static Object[][] getEmptyArrayFailedData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/empty_array_failed_jo.json")},
                {getJsonArrayFromFile("/jsons/empty_array_failed_ja.json")},
                {get("https://api.npoint.io/4a493f95bfef325869cb")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "EmptyObjectFailed")
    static Object[][] getEmptyObjectFailedData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/empty_object_failed_jo.json")},
                {getJsonArrayFromFile("/jsons/empty_object_failed_ja.json")},
                {get("https://api.npoint.io/c9ee90cf6e6d900e6f52")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{}}"""}
        };
    }

    @DataProvider(name = "ExtraFieldInNestedObject")
    static Object[][] getExtraFieldInNestedObjectData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/extra_field_in_nested_object_jo.json")},
                {getJsonArrayFromFile("/jsons/extra_field_in_nested_object_ja.json")},
                {get("https://api.npoint.io/0761cfcc99b600b49d8e")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1},"ccc":[]}}"""}
        };
    }

    @DataProvider(name = "ObjectWithoutField")
    static Object[][] getObjectWithoutFieldData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/object_without_field_jo.json")},
                {getJsonArrayFromFile("/jsons/object_without_field_ja.json")},
                {get("https://api.npoint.io/fc5a11929ca6706c4899")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "ObjectWithInvalidType")
    static Object[][] getObjectWithInvalidTypeData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/object_with_invalid_type_jo.json")},
                {getJsonArrayFromFile("/jsons/object_with_invalid_type_ja.json")},
                {get("https://api.npoint.io/09eaaf3eb24aa2fedecd")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":"string","bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "ObjectWithoutNestedObject")
    static Object[][] getObjectWithoutNestedObjectData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/object_without_nested_object_jo.json")},
                {getJsonArrayFromFile("/jsons/object_without_nested_object_ja.json")},
                {get("https://api.npoint.io/9cf060c5697ec601dd22")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{}}}"""},
        };
    }

    @DataProvider(name = "NestedObjectExtraFieldFailed")
    static Object[][] getNestedObjectExtraFieldFailedData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/nested_object_extra_field_failed_jo.json")},
                {getJsonArrayFromFile("/jsons/nested_object_extra_field_failed_ja.json")},
                {get("https://api.npoint.io/c6d1d182c2ce886ee674")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1,"bbb":"asd"}}}"""}
        };
    }

    @DataProvider(name = "NestedObjectWithoutFieldFailed")
    static Object[][] getNestedObjectWithoutFieldFailedData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/nested_object_without_field_failed_jo.json")},
                {getJsonArrayFromFile("/jsons/nested_object_without_field_failed_ja.json")},
                {get("https://api.npoint.io/d373c5bfc754378555ed")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"bbb":"asd"}}}"""}
        };
    }

    @DataProvider(name = "ArrayHasInvalidObject")
    static Object[][] getArrayHasInvalidObjectData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/array_has_invalid_object_jo.json")},
                {getJsonArrayFromFile("/jsons/array_has_invalid_object_ja.json")},
                {get("https://api.npoint.io/9b7c94e76e0991a42d1b")},
                {"""
                    [{"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1}}},{"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"bb":2}],"g":{"aa":1,"bb":{"aaa":1}}}]"""}
        };
    }

    @DataProvider(name = "InvalidJson")
    static Object[][] getInvalidJson() {
        return new Object[][]{
                {get("https://api.npoint.io/99db55b939e25b519d4d")},
                {"123"}
        };
    }
}