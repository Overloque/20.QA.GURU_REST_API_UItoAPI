package qa.demo.api;

import qa.demo.models.CredentialsModel;
import qa.demo.models.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static qa.demo.specs.LoginSpec.*;

public class LoginApi {
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
