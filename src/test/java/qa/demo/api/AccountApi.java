package qa.demo.api;

import qa.demo.models.authorization.LoginResponseModel;
import qa.demo.models.authorization.RegisterSuccessResponseModel;

import static io.restassured.RestAssured.given;
import static qa.demo.specs.AccountSpec.*;
import static qa.demo.specs.AuthorizationSpec.*;

public class AccountApi {
    public void removeAccount(RegisterSuccessResponseModel response, LoginResponseModel responseLogin) {
        given(authorizationRequestSpec)
                .header("Authorization", "Bearer " + responseLogin.getToken())
                .when()
                .delete("/User/" + response.getUserId())
                .then()
                .spec(removeAccountResponseSpec);
    }
}
