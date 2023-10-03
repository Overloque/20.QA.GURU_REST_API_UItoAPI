package in.reqres.tests;

import in.reqres.models.LoginRequestModel;
import in.reqres.models.LoginResponseErrorModel;
import in.reqres.models.LoginResponseSuccessModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.LoginSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginTests {
    @DisplayName("Проверка успешного входа пользователя")
    @Test
    void checkSuccessfulLoginTest() {
        LoginRequestModel body = new LoginRequestModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("cityslicka");

        LoginResponseSuccessModel response = step("Запрос логина с паролем", () ->
                given(loginRequestSpec)
                        .body(body)
                        .when()
                        .post("/login")
                        .then()
                        .spec(loginSuccessResponseSpec)
                        .extract().as(LoginResponseSuccessModel.class));

        step("Проверка токена", () ->
                assertThat(response.getToken(), equalTo("QpwL5tke4Pnpja7X4")));
    }

    @DisplayName("Проверка неудачной попытки входа пользователя")
    @Test
    void checkMissingEmailFailedLoginTest() {
        LoginRequestModel body = new LoginRequestModel();
        body.setPassword("cityslicka");

        LoginResponseErrorModel response = step("Запрос логина c паролем", () ->
                given(loginRequestSpec)
                        .body(body)
                        .when()
                        .post("/login")
                        .then()
                        .spec(loginErrorResponseSpec)
                        .extract().as(LoginResponseErrorModel.class));

        step("Проверка токена", () ->
                assertThat(response.getError(), equalTo("Missing email or username")));
    }
}
