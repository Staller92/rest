package services;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

import com.api.logging.RestAssuredRequestFilter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestService {

    private static final String BASE_URL = "https://api.frs1.ott.kaltura.com/api_v3/service/ottuser/action";

    public RestService() {
    }

    public static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .addFilter(new RestAssuredRequestFilter())
            .setBaseUri(BASE_URL)
            .setAccept(ContentType.JSON)
            .setContentType(JSON)
            .build();

    public static final ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    public <T> T login(RequestSpecification requestSpec, ResponseSpecification responseSpec, Object request, Class<T> responseClass) throws JsonProcessingException {
        return post(Endpoints.LOGIN, requestSpec, responseSpec, request, responseClass);
    }

    public <T> T createUser(RequestSpecification requestSpec, ResponseSpecification responseSpec, Object request, Class<T> responseClass) throws JsonProcessingException {
        return post(Endpoints.REGISTER, requestSpec, responseSpec, request, responseClass);
    }

    private <T> T post(Endpoints endpoint, RequestSpecification requestSpec, ResponseSpecification responseSpec, Object request, Class<T> responseClass) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String body = objectMapper.writeValueAsString(request);
        return given()
                .spec(requestSpec)
                .body(body)
                .when()
                .post(endpoint.getEndpoint())
                .then()
                .spec(responseSpec)
                .extract()
                .as(responseClass);
    }

    public enum Endpoints {

        REGISTER("/register"),
        LOGIN("/login");

        private String endpoint;

        Endpoints(String endpoint) {
            this.endpoint = endpoint;
        }

        public String getEndpoint() {
            return endpoint;
        }
    }
}
