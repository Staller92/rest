package rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.bin.Login;
import com.api.bin.Registration;
import com.api.bin.Response;
import com.api.bin.Result;
import com.api.data.TestData;
import com.api.logging.TestListener;
import com.fasterxml.jackson.core.JsonProcessingException;

import services.RestService;

@Listeners(TestListener.class)
public class Test_api {

    private RestService restService;
    private Registration registration;
    private Login login;
    private String userName;
    private String password;
    private String id;

    @BeforeClass
    public void init() {
        restService = new RestService();
    }

    @BeforeClass
    public void prepareTestData() {
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        userName = "User" + randomDataGenerator.nextInt(0, 999999999);
        password = "Password" + randomDataGenerator.nextInt(0, 999999999);
        id = String.valueOf(randomDataGenerator.nextLong(0, 999999999));
        registration = TestData.createRegistrationData(userName, password, id);
        login = TestData.createLoginData(userName, password);
    }

    @Test
    public void test_1_create_user() throws JsonProcessingException {
        Response response = restService.createUser(RestService.requestSpec, RestService.responseSpec, registration, Response.class);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response).extracting(Response::getResult).hasFieldOrProperty("id");
        softAssertions.assertThat(response.getResult().getId()).isInstanceOf(String.class);
        softAssertions.assertThat(response).extracting(Response::getResult).hasFieldOrProperty("countryId");
        softAssertions.assertThat(response.getResult().getCountryId()).isInstanceOf(Integer.class);
        softAssertions.assertAll();
    }

    @Test
    public void test_2_login() throws JsonProcessingException {
        SoftAssertions softAssertions = new SoftAssertions();
        Response response = restService.login(RestService.requestSpec, RestService.responseSpec, login, Response.class);
        softAssertions.assertThat(response.getResult()).extracting(Result::getUser).hasFieldOrProperty("lastLoginDate");
        Instant lastLoginDate = Instant.ofEpochMilli(response.getResult().getUser().getLastLoginDate());
        softAssertions.assertThat(lastLoginDate).isStrictlyBetween(Instant.EPOCH, Instant.now());
        softAssertions.assertAll();
    }

    @Test
    public void test_3_error_user_exist() throws JsonProcessingException {
        Response response = restService.createUser(RestService.requestSpec, RestService.responseSpec, registration, Response.class);
        assertThat(response.getResult()).extracting(Result::getError).hasFieldOrPropertyWithValue("message", "User exists");
    }
}


