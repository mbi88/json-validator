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
                {get("https://run.mocky.io/v3/f5f8ac90-e7f2-4974-84e6-b87a75b3123c")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "InvalidType")
    static Object[][] getInvalidTypeData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/invalid_type_jo.json")},
                {getJsonArrayFromFile("/jsons/invalid_type_ja.json")},
                {get("https://run.mocky.io/v3/2b5441c1-b07f-41c3-a1f6-47ae9c46034b")},
                {"""
                    {"a":"1","b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "NullPropertySuccess")
    static Object[][] getNullPropertySuccessData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/null_property_success_jo.json")},
                {get("https://run.mocky.io/v3/a78f332d-d99a-4a77-8c29-896d916ddd1b")},
                {"""
                    {"a":1,"b":"a","c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "NullPropertyFailed")
    static Object[][] getNullPropertyFailedData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/null_property_failed_jo.json")},
                {getJsonArrayFromFile("/jsons/null_property_failed_ja.json")},
                {get("https://run.mocky.io/v3/a6b86e19-8089-4cdf-98aa-1ff2e10e6316")},
                {"""
                    {"a":1,"b":1,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "EmptyObject")
    static Object[][] getEmptyObjectData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/empty_object_jo.json")},
                {get("https://run.mocky.io/v3/ed5cee7e-6073-4893-b784-e13b4e1ac30a")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{"a":1},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "EmptyArray")
    static Object[][] getEmptyArrayData() {
        return new Object[][]{
                {getJsonArrayFromFile("/jsons/empty_array_ja.json")},
                {get("https://run.mocky.io/v3/6ea2cc24-1914-4ba3-80cf-d63f25a71f74")},
                {"""
                    [{"a":1,"b":null,"c":"1","d":[1],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1}}},{"a":1,"b":null,"c":"1","d":[1],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1}}}]"""}
        };
    }

    @DataProvider(name = "ValidateArray")
    static Object[][] getValidateArrayData() {
        return new Object[][]{
                {getJsonArrayFromFile("/jsons/validate_array_ja.json")},
                {get("https://run.mocky.io/v3/a38c3865-ea31-4fed-b813-33aa7aa45484")},
                {"""
                    [{"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1}],"g":{"aa":1,"bb":{"aaa": 1}}}, {"a": 2, "b": null, "c": "1", "d": [], "e": {}, "f": [{"aa": 1}], "g": {"aa": 1, "bb": {"aaa": 1}}}]"""}
        };
    }

    @DataProvider(name = "ExtraField")
    static Object[][] getExtraFieldData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/extra_field_jo.json")},
                {getJsonArrayFromFile("/jsons/extra_field_ja.json")},
                {get("https://run.mocky.io/v3/65928176-8d46-43cc-bf2e-056a83415b8b")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1,"a":1}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "ExtraObject")
    static Object[][] getExtraObjectData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/extra_object_jo.json")},
                {getJsonArrayFromFile("/jsons/extra_object_ja.json")},
                {get("https://run.mocky.io/v3/3346f8c6-0ae8-4d90-9f70-8a375b7b00a8")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"bb":1}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "WithoutField")
    static Object[][] getWithoutFieldData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/wo_field_jo.json")},
                {getJsonArrayFromFile("/jsons/wo_field_ja.json")},
                {get("https://run.mocky.io/v3/97c8d34e-1e97-4c66-9450-ebebeaf2e093")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"bb":1}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "InvalidValue")
    static Object[][] getInvalidValueData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/invalid_value_jo.json")},
                {getJsonArrayFromFile("/jsons/invalid_value_ja.json")},
                {get("https://run.mocky.io/v3/5ddf3f30-05bd-4836-a4da-bbba8bbd1695")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":"1"}],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "EmptyArrayFailed")
    static Object[][] getEmptyArrayFailedData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/empty_array_failed_jo.json")},
                {getJsonArrayFromFile("/jsons/empty_array_failed_ja.json")},
                {get("https://run.mocky.io/v3/0b91c4b4-9dde-4e19-8a70-b81211ab11a5")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[],"g":{"aa":1,"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "EmptyObjectFailed")
    static Object[][] getEmptyObjectFailedData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/empty_object_failed_jo.json")},
                {getJsonArrayFromFile("/jsons/empty_object_failed_ja.json")},
                {get("https://run.mocky.io/v3/19f8ddcd-6e0a-41c4-87a6-b59bcc98f1a4")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{}}"""}
        };
    }

    @DataProvider(name = "ExtraFieldInNestedObject")
    static Object[][] getExtraFieldInNestedObjectData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/extra_field_in_nested_object_jo.json")},
                {getJsonArrayFromFile("/jsons/extra_field_in_nested_object_ja.json")},
                {get("https://run.mocky.io/v3/a6cf579f-468c-46f0-ae2a-968f581679cc")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1},"ccc":[]}}"""}
        };
    }

    @DataProvider(name = "ObjectWithoutField")
    static Object[][] getObjectWithoutFieldData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/object_without_field_jo.json")},
                {getJsonArrayFromFile("/jsons/object_without_field_ja.json")},
                {get("https://run.mocky.io/v3/b22e4017-a3b4-4d48-8bd3-ed45b935666e")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "ObjectWithInvalidType")
    static Object[][] getObjectWithInvalidTypeData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/object_with_invalid_type_jo.json")},
                {getJsonArrayFromFile("/jsons/object_with_invalid_type_ja.json")},
                {get("https://run.mocky.io/v3/5f169c04-1c5f-4a5f-926b-0c9396bb9152")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":"string","bb":{"aaa":1}}}"""}
        };
    }

    @DataProvider(name = "ObjectWithoutNestedObject")
    static Object[][] getObjectWithoutNestedObjectData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/object_without_nested_object_jo.json")},
                {getJsonArrayFromFile("/jsons/object_without_nested_object_ja.json")},
                {get("https://run.mocky.io/v3/8e81eec9-f202-401d-9339-cc8fd53d28e3")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{}}}"""},
        };
    }

    @DataProvider(name = "NestedObjectExtraFieldFailed")
    static Object[][] getNestedObjectExtraFieldFailedData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/nested_object_extra_field_failed_jo.json")},
                {getJsonArrayFromFile("/jsons/nested_object_extra_field_failed_ja.json")},
                {get("https://run.mocky.io/v3/08a4751b-108d-4d4e-977c-15cce8b93262")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1,"bbb":"asd"}}}"""}
        };
    }

    @DataProvider(name = "NestedObjectWithoutFieldFailed")
    static Object[][] getNestedObjectWithoutFieldFailedData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/nested_object_without_field_failed_jo.json")},
                {getJsonArrayFromFile("/jsons/nested_object_without_field_failed_ja.json")},
                {get("https://run.mocky.io/v3/a60089a2-5451-4611-ab63-1f03d1f83da4")},
                {"""
                    {"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"bbb":"asd"}}}"""}
        };
    }

    @DataProvider(name = "ArrayHasInvalidObject")
    static Object[][] getArrayHasInvalidObjectData() {
        return new Object[][]{
                {getJsonFromFile("/jsons/array_has_invalid_object_jo.json")},
                {getJsonArrayFromFile("/jsons/array_has_invalid_object_ja.json")},
                {get("https://run.mocky.io/v3/f3a70a7d-e812-41cd-b4be-867d5f76e472")},
                {"""
                    [{"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"aa":2}],"g":{"aa":1,"bb":{"aaa":1}}},{"a":1,"b":null,"c":"1","d":[],"e":{},"f":[{"aa":1},{"bb":2}],"g":{"aa":1,"bb":{"aaa":1}}}]"""}
        };
    }

    @DataProvider(name = "InvalidJson")
    static Object[][] getInvalidJson() {
        return new Object[][]{
                {get("https://run.mocky.io/v3/75a701ba-bd62-4679-8351-7c5b5b8a62ca")},
                {"123"}
        };
    }
}