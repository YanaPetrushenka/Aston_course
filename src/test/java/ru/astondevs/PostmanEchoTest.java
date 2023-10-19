package ru.astondevs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

class PostmanEchoTest {

    @BeforeAll
    static void init() {
        var config = ResourceBundle.getBundle("config");
        RestAssured.baseURI = config.getString("url");
    }

    @Test
    void testGetRequest() {
        given()
                .param("foo1", "bar1")
                .param("foo2", "bar2")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.user-agent", notNullValue())
                .body("headers.accept", equalTo("*/*"));
    }

    @Test
    void testPostRawText() {
        given()
                .body("This is expected to be sent back as part of response body.")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data", equalTo("This is expected to be sent back as part of response body."))
                .body("args", anEmptyMap())
                .body("files", anEmptyMap())
                .body("form", anEmptyMap())
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.content-length", notNullValue())
                .body("headers.content-type", startsWith("text/plain"))
                .body("headers.user-agent", notNullValue())
                .body("headers.accept", equalTo("*/*"))
                .body("headers.accept-encoding", equalTo("gzip,deflate"));
    }

    @Test
    void testPostFormData() {
        given()
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("args", anEmptyMap())
                .body("data", emptyString())
                .body("files", anEmptyMap())
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.content-length", notNullValue())
                .body("headers.user-agent", notNullValue())
                .body("headers.accept", equalTo("*/*"))
                .body("headers.content-type", equalTo("application/x-www-form-urlencoded; charset=utf-8"))
                .body("json.foo1", equalTo("bar1"))
                .body("json.foo2", equalTo("bar2"));
    }

    @Test
    void testPutRequests() {
        given()
                .header("Content-Type", "text/plain")
                .body("This is expected to be sent back as part of response body.")
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("args", anEmptyMap())
                .body("data", equalTo("This is expected to be sent back as part of response body."))
                .body("files", anEmptyMap())
                .body("form", anEmptyMap())
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.content-length", notNullValue())
                .body("headers.content-type", startsWith("text/plain"))
                .body("headers.user-agent", notNullValue())
                .body("headers.accept", equalTo("*/*"))
                .body("json", nullValue());
    }

    @Test
    void testPatchRequests() {
        given()
                .header("Content-Type", "text/plain")
                .body("This is expected to be sent back as part of response body.")
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("args", anEmptyMap())
                .body("data", equalTo("This is expected to be sent back as part of response body."))
                .body("files", anEmptyMap())
                .body("form", anEmptyMap())
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.content-length", notNullValue())
                .body("headers.content-type", startsWith("text/plain"))
                .body("headers.user-agent", notNullValue())
                .body("headers.accept", equalTo("*/*"))
                .body("json", nullValue());
    }

    @Test
    void testDeleteRequests() {
        given()
                .header("Content-Type", "text/plain")
                .body("This is expected to be sent back as part of response body.")
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.content-length", notNullValue())
                .body("headers.content-type", startsWith("text/plain"))
                .body("headers.user-agent", notNullValue())
                .body("headers.accept", equalTo("*/*"))
                .body("json", nullValue())
                .body("url", equalTo("https://postman-echo.com/delete"));
    }
}
