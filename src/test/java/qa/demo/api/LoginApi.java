package qa.demo.api;

import io.qameta.allure.Step;
import qa.demo.models.authorization.CredentialsModel;
import qa.demo.models.authorization.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static qa.demo.specs.LoginSpec.*;

public class LoginApi {
    @Step("Авторизация на сайте demoqa.com")
    public LoginResponseModel login(CredentialsModel creds) {
        return given(loginRequestSpec)
                .body(creds)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(loginResponseSpec)
                .extract().as(LoginResponseModel.class);
    }
}
