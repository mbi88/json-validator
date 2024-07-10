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
                {get("https://run.mocky.io/v3/dbece8ee-ac02-4a59-a221-5bacec4377cd")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "InvalidType")
    static Object[][] getInvalidTypeData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/invalid_type_jo.json")},
                {getJsonArrayFromFile("/jsons/invalid_type_ja.json")},
                {get("https://run.mocky.io/v3/51748208-2063-46fe-ab63-62b44280fd96")},
                {"""
                    {"a":"1","b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "NullPropertySuccess")
    static Object[][] getNullPropertySuccessData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/null_property_success_jo.json")},
                {get("https://run.mocky.io/v3/40073f15-ee9b-458f-b7ba-c4efbe847368")},
                {"""
                    {"a":1,"b":"a","c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "NullPropertyFailed")
    static Object[][] getNullPropertyFailedData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/null_property_failed_jo.json")},
                {getJsonArrayFromFile("/jsons/null_property_failed_ja.json")},
                {get("https://run.mocky.io/v3/14e0d9c0-233d-46ea-a09d-d52971238a42")},
                {"""
                    {"a":1,"b":1,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "EmptyObject")
    static Object[][] getEmptyObjectData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/empty_object_jo.json")},
                {get("https://run.mocky.io/v3/31e7d4b7-4c9c-4693-9cea-a654a644e70f")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{"a":1},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "EmptyArray")
    static Object[][] getEmptyArrayData() {
        return new Object[][]{
                {getJsonArrayFromFile("/jsons/empty_array_ja.json")},
                {get("https://run.mocky.io/v3/1555976b-19cc-4e66-83af-b6c8412099a6")},
                {"""
                    [{"a":1,"b":null,"c":"1","d":[1],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1}}},{"a":1,"b":null,"c":"1","d":[1],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1}}}]"""}
        };
    }

    @DataProvider(name = "ValidateArray")
    static Object[][] getValidateArrayData() {
        return new Object[][]{
                {getJsonArrayFromFile("/jsons/validate_array_ja.json")},
                {get("https://run.mocky.io/v3/8045379e-7e5f-4ac0-9cd6-24c63fd6c147")},
                {"""
                    [{"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1}],"g":{"aa":1,"bb":{"aaa": 1}}}, {"a": 2, "b": null, "c": "1", "d": [], "e": {}, "f": [{"aa": 1}], "g": {"aa": 1, "bb": {"aaa": 1}}}]"""}
        };
    }

    @DataProvider(name = "ExtraField")
    static Object[][] getExtraFieldData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/extra_field_jo.json")},
                {getJsonArrayFromFile("/jsons/extra_field_ja.json")},
                {get("https://run.mocky.io/v3/69edb648-093a-4798-9fd4-2d017f799140")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1,"a":1}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "ExtraObject")
    static Object[][] getExtraObjectData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/extra_object_jo.json")},
                {getJsonArrayFromFile("/jsons/extra_object_ja.json")},
                {get("https://run.mocky.io/v3/10ee30ca-e167-490c-85a9-4df783a2fe1d")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"bb":1}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "WithoutField")
    static Object[][] getWithoutFieldData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/wo_field_jo.json")},
                {getJsonArrayFromFile("/jsons/wo_field_ja.json")},
                {get("https://run.mocky.io/v3/21671812-a4ce-42ef-b773-b881853e69bb")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"bb":1}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "InvalidValue")
    static Object[][] getInvalidValueData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/invalid_value_jo.json")},
                {getJsonArrayFromFile("/jsons/invalid_value_ja.json")},
                {get("https://run.mocky.io/v3/7ba00948-23d6-4baf-83ea-2f478094b803")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":"1"}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "EmptyArrayFailed")
    static Object[][] getEmptyArrayFailedData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/empty_array_failed_jo.json")},
                {getJsonArrayFromFile("/jsons/empty_array_failed_ja.json")},
                {get("https://run.mocky.io/v3/34b67a35-7191-4c07-8f35-d41ff07025e5")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "EmptyObjectFailed")
    static Object[][] getEmptyObjectFailedData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/empty_object_failed_jo.json")},
                {getJsonArrayFromFile("/jsons/empty_object_failed_ja.json")},
                {get("https://run.mocky.io/v3/3b325b1b-c8ff-4e16-8df5-6cf1c1f23222")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{}}"""}
        };
    }

    @DataProvider(name = "ExtraFieldInNestedObject")
    static Object[][] getExtraFieldInNestedObjectData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/extra_field_in_nested_object_jo.json")},
                {getJsonArrayFromFile("/jsons/extra_field_in_nested_object_ja.json")},
                {get("https://run.mocky.io/v3/99c91dfd-ccf6-4d43-aeb4-7008f6af2b19")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1},"ccc":[]}}"""}
        };
    }

    @DataProvider(name = "ObjectWithoutField")
    static Object[][] getObjectWithoutFieldData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/object_without_field_jo.json")},
                {getJsonArrayFromFile("/jsons/object_without_field_ja.json")},
                {get("https://run.mocky.io/v3/c41f7406-7bb7-4e46-8934-bbec96c1f23a")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "ObjectWithInvalidType")
    static Object[][] getObjectWithInvalidTypeData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/object_with_invalid_type_jo.json")},
                {getJsonArrayFromFile("/jsons/object_with_invalid_type_ja.json")},
                {get("https://run.mocky.io/v3/f0d208e2-3641-482f-bbaa-1575a934c338")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":"string","bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "ObjectWithoutNestedObject")
    static Object[][] getObjectWithoutNestedObjectData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/object_without_nested_object_jo.json")},
                {getJsonArrayFromFile("/jsons/object_without_nested_object_ja.json")},
                {get("https://run.mocky.io/v3/38fa9b62-1d97-49e7-b2a2-acf0a033451e")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{}}}"""},
        };
    }

    @DataProvider(name = "NestedObjectExtraFieldFailed")
    static Object[][] getNestedObjectExtraFieldFailedData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/nested_object_extra_field_failed_jo.json")},
                {getJsonArrayFromFile("/jsons/nested_object_extra_field_failed_ja.json")},
                {get("https://run.mocky.io/v3/f0a98504-9bf6-4d4b-a665-1b0fb0d1482a")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1,"bbb":"asd"}}}"""}
        };
    }

    @DataProvider(name = "NestedObjectWithoutFieldFailed")
    static Object[][] getNestedObjectWithoutFieldFailedData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/nested_object_without_field_failed_jo.json")},
                {getJsonArrayFromFile("/jsons/nested_object_without_field_failed_ja.json")},
                {get("https://run.mocky.io/v3/c6c02350-0582-4471-aa2e-3e76e472eae9")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"bbb":"asd"}}}"""}
        };
    }

    @DataProvider(name = "ArrayHasInvalidObject")
    static Object[][] getArrayHasInvalidObjectData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/array_has_invalid_object_jo.json")},
                {getJsonArrayFromFile("/jsons/array_has_invalid_object_ja.json")},
                {get("https://run.mocky.io/v3/cfe05595-613b-412a-ba67-b1bed4fa90e2")},
                {"""
                    [{"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1}}},{"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"bb":2}],"g":{"aa":1,"bb":{"aaa":1}}}]"""}
        };
    }

    @DataProvider(name = "InvalidJson")
    static Object[][] getInvalidJson() {
        return new Object[][]{
                {get("https://run.mocky.io/v3/6ac6631e-093e-47e6-87fe-4609b587b927")},
                {"123"}
        };
    }
}