package qa.demo.tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import qa.demo.models.AddingBookToListModel;
import qa.demo.models.IsbnModel;
import qa.demo.models.LoginResponseModel;
import qa.demo.models.RemovingBookFromListModel;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static qa.demo.utils.TestData.*;

public class RemoveBookFromListTest extends BaseTest {
    @Test
    @DisplayName("Проверка удаления книги из списка")
    void checkSuccessfulRemoveBookFromListTest() {
        LoginResponseModel response = loginApi.login(creds);

        IsbnModel isbnModel = new IsbnModel();
        isbnModel.setIsbn(bookId);

        List<IsbnModel> collectionIsbn = new ArrayList<>();
        collectionIsbn.add(isbnModel);

        AddingBookToListModel booksList = new AddingBookToListModel();
        booksList.setUserId(response.getUserId());
        booksList.setCollection(collectionIsbn);

//        RemovingBookFromListModel removingBooksList = new RemovingBookFromListModel();
//        removingBooksList.setIsbn(bookId);
//        removingBooksList.setUserId(response.getUserId());

        bookApi.addBook(response, booksList);
    }
}
