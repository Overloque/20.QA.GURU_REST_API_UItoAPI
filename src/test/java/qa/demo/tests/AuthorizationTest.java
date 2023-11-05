package qa.demo.tests;

import com.google.gson.Gson;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import qa.demo.models.authorization.LoginResponseModel;
import qa.demo.models.authorization.RegisterErrorResponseModel;
import qa.demo.models.authorization.RegisterSuccessResponseModel;

import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static qa.demo.utils.TestDataVariables.*;

@Epic(value = "Проверка апи сайта https://demoqa.com")
@Feature(value = "Проверка методов, связанных с авторизацией/регистрацией")
@Link(name = "demoqa", value = "https://demoqa.com/")
@Owner("Overloque")
@Tag("account")
public class AuthorizationTest extends BaseTest {
    @Test
    @Severity(CRITICAL)
    @DisplayName("Проверка полей после авторизации")
    void checkLoginFieldsTest() {
        step("Вызов метода для генерации токена пользователя", () ->
                authorizationApi.generateToken(existCreds));

        step("Вызов метода авторизации", () -> {
            LoginResponseModel response = authorizationApi.login(existCreds);
            System.setProperty("response", new Gson().toJson(response));
        });

        step("Проверка полей username, password, userId, created_date", () -> {
            String responseJson = System.getProperty("response");
            LoginResponseModel response = new Gson().fromJson(responseJson, LoginResponseModel.class);

            assertThat(response.getUserId())
                    .as("Уникальный идентификатор пользователя")
                    .isEqualTo("e889fb62-ddce-411a-a406-76e38d3c66b8");

            assertThat(response.getUsername())
                    .as("Имя пользователя")
                    .isEqualTo("TestDemoUser1");

            assertThat(response.getPassword())
                    .as("Пароль пользователя")
                    .isEqualTo("TestDemoUser1!");

            assertNotNull(response.getCreatedDate());
        });
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Проверка полей после успешной регистрации")
    void checkSuccessRegisterTest() {
        step("Вызов метода регистрации", () -> {
            RegisterSuccessResponseModel response = authorizationApi.registerSuccess(randomCreds);
            System.setProperty("response", new Gson().toJson(response));
        });

        step("Проверка полей userId, books, username", () -> {
            String responseJson = System.getProperty("response");
            RegisterSuccessResponseModel response = new Gson().fromJson(responseJson, RegisterSuccessResponseModel.class);

            assertNotNull(response.getUserId());
            assertNotNull(response.getBooks());
            assertNotNull(response.getUsername());
        });
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Проверка сообщения после регистрации под существующим пользователем")
    void checkErrorRegisterTest() {
        step("Вызов метода регистрации", () -> {
            RegisterErrorResponseModel response = authorizationApi.registerError(existCreds);
            System.setProperty("response", new Gson().toJson(response));
        });

        step("Проверка полей userId, books, username", () -> {
            String responseJson = System.getProperty("response");
            RegisterErrorResponseModel response = new Gson().fromJson(responseJson, RegisterErrorResponseModel.class);

            assertThat(response.getCode())
                    .as("Код ошибки")
                    .isEqualTo("1204");

            assertThat(response.getMessage())
                    .as("Сообщение ошибки")
                    .isEqualTo("User exists!");
        });
    }
}