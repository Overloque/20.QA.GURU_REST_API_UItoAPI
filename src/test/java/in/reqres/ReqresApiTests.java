package in.reqres;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class ReqresApiTests extends BaseTest {
    @DisplayName("Проверка наличия определённого email из списка")
    @Test
    void checkExistingEmailInArrayTest() {
        given()
                .log().uri()
                .log().method()
                .when()
                .get("/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.email", hasItem("michael.lawson@reqres.in"));
    }

    @DisplayName("Проверка получения определённого пользователя по его id")
    @Test
    void checkExistingUserTest() {
        given()
                .log().uri()
                .log().method()
                .when()
                .get("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.last_name", is("Weaver"));
    }

    @DisplayName("Проверка ошибки по получению несуществующего пользователя")
    @Test
    void checkNonExistingUserTest() {
        given()
                .log().uri()
                .log().method()
                .when()
                .get("/users/25")
                .then()
                .log().status()
                .statusCode(404);
    }

    @DisplayName("Проверка успешного создания пользователя")
    @Test
    void checkNewUserTest() {
        String bodyData = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(bodyData)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @DisplayName("Проверка успешного создания пользователя")
    @Test
    void checkRegisterUserTest() {
        String bodyData = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(bodyData)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"))
                .body("id", notNullValue());
    }
}
