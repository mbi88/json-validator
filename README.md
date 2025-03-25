[![Java CI with Gradle](https://github.com/mbi88/json-validator/actions/workflows/gradle.yml/badge.svg)](https://github.com/mbi88/json-validator/actions/workflows/gradle.yml)
[![codecov](https://codecov.io/gh/mbi88/json-validator/branch/master/graph/badge.svg)](https://codecov.io/gh/mbi88/json-validator)
[![Latest Version](https://img.shields.io/github/v/tag/mbi88/json-validator?label=version)](https://github.com/mbi88/json-validator/releases)
[![jitpack](https://jitpack.io/v/mbi88/json-validator.svg)](https://jitpack.io/#mbi88/json-validator)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)


# json-validator

Simple and flexible JSON Schema validator built on top of [Everit JSON Schema Validator](https://github.com/everit-org/json-schema).

Supports:

✅ `JSONObject`, `JSONArray`  
✅ Raw JSON as `String`  
✅ REST API responses (`Rest-Assured`)  
✅ Custom error messages with all validation issues and response pretty-printed  
✅ Designed for test automation (TestNG / JUnit ready)  
✅ Lightweight and dependency-minimal

---

## Installation

<details>
<summary>Gradle (Kotlin DSL)</summary>

```kotlin  
implementation("com.mbi:json-validator:1.0")  
```

</details>  
<details>  
<summary>Gradle (Groovy DSL)</summary>

```groovy  
implementation 'com.mbi:json-validator:1.0'  
```

</details>

---

## Example

### ✅ Validating `JSONObject`

```java
import com.mbi.JsonValidator;
import org.json.JSONObject;

public class ObjectExample {
    public static void main(String[] args) {
        var schema = new JSONObject("""
                {
                    "type": "object",
                    "properties": {
                        "id": { "type": "integer"},
                        "name": {"type": "string"}
                    },
                    "required": ["id", "name"]
                }""");

        var json = new JSONObject("""
                {
                    "id": 123,
                    "name": "Alice"
                }""");

        new JsonValidator().validate(schema, json);
    }
}
```

### ✅ Validating `JSONArray`

```java
import com.mbi.JsonValidator;
import org.json.JSONArray;
import org.json.JSONObject;

public class ArrayExample {
    public static void main(String[] args) {
        var schema = new JSONObject("""
                {
                    "type": "array",
                    "items": {
                        "type": "object",
                        "properties": {
                            "id": { "type": "integer" },
                            "active": { "type": "boolean" }
                        },
                        "required": ["id", "active"]
                    }
                }""");

        var json = new JSONArray("""
                [
                    { "id": 1, "active": true },
                    { "id": 2, "active": false }
                ]""");

        new JsonValidator().validate(schema, json);
    }
}
```

### ✅ Validating `Response` (Rest-Assured)

```java
import com.mbi.JsonValidator;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class ResponseExample {

    public static void main(String[] args) {
        var response = given()
                .when()
                .get("/api/user/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        var schema = new JSONObject("""
                {
                    "type": "object",
                    "properties": {
                        "id": { "type": "integer" },
                        "email": { "type": "string", "format": "email" }
                    },
                    "required": ["id", "email"]
                }""");

        new JsonValidator().validate(schema, response);
    }
}
```

### ❌ Error message example

If validation fails, you’ll get a detailed message like this:

```
#: required key [name] not found  
#/id: expected type: Integer, found: String

Response:
{
    "id": "abc"
}
```

---

## See also

- [JSON Schema](https://json-schema.org/)
- [Online Schema Generator](https://jsonschema.net/)
- [Online Schema Validator](http://www.jsonschemavalidator.net/)
- [Everit JSON Schema Validator](https://github.com/everit-org/json-schema)

---

✅ Lightweight & focused · ✨ Ready for test automation · 💥 Validates and explains errors

---

## License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.
