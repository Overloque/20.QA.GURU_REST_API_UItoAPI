package in.reqres.tests;

import in.reqres.models.CreateUserRequestModel;
import in.reqres.models.CreateUserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.CreateUserSpec.createUserRequestSpec;
import static in.reqres.specs.CreateUserSpec.createUserResponseSuccessSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreatingUserTests {
    @DisplayName("Проверка успешного создания пользователя")
    @Test
    void checkSuccessfulCreationUserTest() {
        CreateUserRequestModel body = new CreateUserRequestModel();
        body.setName("morpheus");
        body.setJob("leader");

        CreateUserResponseModel response = step("Запрос данных о пользователе", () ->
                given(createUserRequestSpec)
                        .body(body)
                        .when()
                        .post("/users")
                        .then()
                        .spec(createUserResponseSuccessSpec)
                        .extract().as(CreateUserResponseModel.class));

        step("Проверка полей в ответе", () -> {
            assertThat(response.getName(), equalTo("morpheus"));
            assertThat(response.getJob(), equalTo("leader"));
            assertNotNull(response.getId());
            assertNotNull(response.getCreatedAt());
        });
    }
}
