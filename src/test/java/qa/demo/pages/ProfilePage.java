package qa.demo.pages;

import org.openqa.selenium.Cookie;
import qa.demo.models.authorization.LoginResponseModel;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProfilePage {
    public ProfilePage addCookies(LoginResponseModel response) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", response.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", response.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", response.getExpires()));

        return this;
    }

    public ProfilePage openProfilePage() {
        open("/profile");

        return this;
    }

    public ProfilePage checkEmptyTable() {
        $("[id='see-book-Git Pocket Guide']").shouldNotBe(visible);

        return this;
    }
}
