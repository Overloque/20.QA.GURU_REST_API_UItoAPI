package qa.demo.api;

import qa.demo.models.authorization.*;

import static io.restassured.RestAssured.given;
import static qa.demo.specs.AuthorizationSpec.*;

public class AuthorizationApi {
    public LoginResponseModel login(CredentialsModel creds) {
        return given(authorizationRequestSpec)
                .body(creds)
                .when()
                .post("/Login")
                .then()
                .spec(loginResponseSpec)
                .extract().as(LoginResponseModel.class);
    }

    public GenerateTokenResponseModel generateToken(CredentialsModel creds) {
        return given(authorizationRequestSpec)
                .body(creds)
                .when()
                .post("/GenerateToken")
                .then()
                .spec(loginResponseSpec)
                .extract().as(GenerateTokenResponseModel.class);
    }

    public RegisterSuccessResponseModel registerSuccess(CredentialsModel creds) {
        return given(authorizationRequestSpec)
                .body(creds)
                .when()
                .post("/User")
                .then()
                .spec(registerSuccessResponseSpec)
                .extract().as(RegisterSuccessResponseModel.class);
    }

    public RegisterErrorResponseModel registerError(CredentialsModel creds) {
        return given(authorizationRequestSpec)
                .body(creds)
                .when()
                .post("/User")
                .then()
                .spec(registerFailedResponseSpec)
                .extract().as(RegisterErrorResponseModel.class);
    }
}
