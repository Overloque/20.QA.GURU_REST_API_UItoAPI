package qa.demo.tests;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import qa.demo.api.BookApi;
import qa.demo.api.LoginApi;

public class BaseTest {
    LoginApi loginApi = new LoginApi();
    BookApi bookApi = new BookApi();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        RestAssured.baseURI = "https://demoqa.com";
        Configuration.browserSize = "2560x1440";
        Configuration.pageLoadStrategy = "eager";
    }
}
