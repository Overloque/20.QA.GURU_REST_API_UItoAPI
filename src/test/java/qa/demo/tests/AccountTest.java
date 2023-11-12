package qa.demo.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import qa.demo.api.AccountApi;
import qa.demo.models.authorization.GenerateTokenResponseModel;
import qa.demo.models.authorization.LoginResponseModel;
import qa.demo.models.authorization.RegisterSuccessResponseModel;

import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static qa.demo.utils.TestDataVariables.*;

@Epic(value = "Проверка апи сайта https://demoqa.com")
@Feature(value = "Проверка методов, связанных с аккаунтом")
@Link(value = "demoqa", url = "https://demoqa.com")
@Owner("Overloque")
@Tag("account")
public class AccountTest extends BaseTest {
    AccountApi accountApi = new AccountApi();

    @Test
    @Severity(CRITICAL)
    @DisplayName("Проверка удаления аккаунта")
    void checkSuccessRegisterTest() {
        final RegisterSuccessResponseModel registerResponse = step("Вызов метода регистарции", () ->
                authorizationApi.registerSuccess(randomCreds));

        step("Проверка полей userId, books, username", () -> {
            assertNotNull(registerResponse.getUserId());
            assertNotNull(registerResponse.getBooks());
            assertNotNull(registerResponse.getUsername());
        });

        final GenerateTokenResponseModel generateResponse = step("Вызова метода для генерации токена пользователя", () ->
                authorizationApi.generateToken(randomCreds));

        final LoginResponseModel loginResponse = step("Вызов метода авторизации для нового пользователя", () ->
                authorizationApi.login(randomCreds));

        step("Удаление созданного аккаунта", () -> {
            accountApi.removeAccount(registerResponse, loginResponse);
        });

        final GenerateTokenResponseModel generateNewResponse = step("Вызова метода для генерации токена того же пользователя", () ->
                authorizationApi.generateToken(randomCreds));

        step("Проверка сообщения и статуса при попытке входа на удаленный аккаунт", () -> {
            assertThat(generateNewResponse.getResult())
                    .as("Соообщение об ошибке")
                    .isEqualTo("User authorization failed.");

            assertThat(generateNewResponse.getStatus())
                    .as("Статус генерации токена")
                    .isEqualTo("Failed");
        });
    }
}
