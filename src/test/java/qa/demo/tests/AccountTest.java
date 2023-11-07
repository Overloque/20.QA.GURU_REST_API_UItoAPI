package qa.demo.tests;

import com.google.gson.Gson;
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
        step("Вызов метода регистрации", () -> {
            RegisterSuccessResponseModel response = authorizationApi.registerSuccess(randomCreds);

            step("Проверка полей userId, books, username", () -> {
                assertNotNull(response.getUserId());
                assertNotNull(response.getBooks());
                assertNotNull(response.getUsername());
            });

            step("Вызова метода для генерации токена пользователя", () ->
                    authorizationApi.generateToken(randomCreds));

            step("Вызов метода авторизации для нового пользователя", () -> {
                LoginResponseModel loginResponseModel = authorizationApi.login(randomCreds);

                step("Удаление созданного аккаунта", () -> {
                    accountApi.removeAccount(response, loginResponseModel);
                });
            });

            step("Проверка сообщения и статуса при попытке входа на удаленный аккаунт", () -> {
                GenerateTokenResponseModel generateTokenResponse = authorizationApi.generateToken(randomCreds);

                assertThat(generateTokenResponse.getResult())
                        .as("Соообщение об ошибке")
                        .isEqualTo("User authorization failed.");

                assertThat(generateTokenResponse.getStatus())
                        .as("Статус генерации токена")
                        .isEqualTo("Failed");
            });
        });
    }
}
