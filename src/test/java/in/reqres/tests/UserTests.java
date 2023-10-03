package in.reqres.tests;

import in.reqres.models.UserArrayResponseModel;
import in.reqres.models.UserSingleModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.UserSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserTests {
    @DisplayName("Проверка полей пользователей из общего списка")
    @Test
    void checkExistingFieldsInArrayTest() {
        UserArrayResponseModel response = step("Запрос получения данных пользователей", () ->
                given(userRequestSpec)
                        .when()
                        .get("/users?page=2")
                        .then()
                        .spec(userResponseSuccessfulSpec)
                        .extract().as(UserArrayResponseModel.class));

        step("Проверка полей пользователей", () -> {
            assertThat(response.getPage(), equalTo(2));
            assertThat(response.getPer_page(), equalTo(6));
            assertThat(response.getTotal(), equalTo(12));
            assertThat(response.getTotal_pages(), equalTo(2));
            assertThat(response.getData().stream().findFirst().get().getEmail(), equalTo("michael.lawson@reqres.in"));
            assertThat(response.getData().get(0).getFirst_name(), equalTo("Michael"));
        });
    }

    @DisplayName("Проверка полей определённого пользователя")
    @Test
    void checkExistingUserTest() {
        UserSingleModel response = step("Запрос получения данных пользователя", () ->
                given(userRequestSpec)
                        .when()
                        .get("/users/2")
                        .then()
                        .spec(userResponseSuccessfulSpec)
                        .extract().as(UserSingleModel.class));

        step("Проверка полей пользователя", () -> {
            assertThat(response.getData().getId(), equalTo(2));
            assertThat(response.getData().getEmail(), equalTo("janet.weaver@reqres.in"));
            assertThat(response.getData().getFirst_name(), equalTo("Janet"));
            assertThat(response.getData().getLast_name(), equalTo("Weaver"));
            assertThat(response.getData().getAvatar(), equalTo("https://reqres.in/img/faces/2-image.jpg"));
            assertThat(response.getSupport().getUrl(), equalTo("https://reqres.in/#support-heading"));
            assertThat(response.getSupport().getText(), equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
        });
    }

    @DisplayName("Проверка ошибки по получению несуществующего пользователя")
    @Test
    void checkNonExistingUserTest() {
        UserSingleModel response = step("Запрос получения данных пользователя", () ->
                given(userRequestSpec)
                        .when()
                        .get("/users/25")
                        .then()
                        .spec(userResponseErrorSpec)
                        .extract().as(UserSingleModel.class));
    }
}
