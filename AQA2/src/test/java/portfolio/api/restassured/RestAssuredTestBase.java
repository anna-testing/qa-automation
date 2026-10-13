package portfolio.api.restassured;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import portfolio.api.config.ApiConfig;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

abstract class RestAssuredTestBase {
    @BeforeEach
    void configureRestAssured() {
        RestAssured.baseURI = ApiConfig.BASE_URL;
    }

    protected io.restassured.specification.RequestSpecification request() {
        return RestAssured.given().header("x-api-key", ApiConfig.API_KEY).contentType("application/json");
    }

    protected void assertSuccessful(ValidatableResponse response) {
        response.statusCode(anyOf(is(200), is(201), is(204)));
    }
}
