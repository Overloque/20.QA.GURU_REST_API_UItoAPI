package qa.demo.tests;

import io.qameta.allure.Link;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qa.demo.models.books.AddingBookToListModel;
import qa.demo.models.books.IsbnModel;
import qa.demo.models.authorization.LoginResponseModel;
import qa.demo.models.books.RemovingBookFromListModel;
import qa.demo.pages.ProfilePage;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static qa.demo.utils.TestData.*;

public class RemoveBookFromListTest extends BaseTest {
    @Test
    @DisplayName("Проверка удаления книги из списка")
    @Link(value = "demoqa", url = "https://demoqa.com/profile")
    void checkSuccessfulRemoveBookFromListTest() {
        ProfilePage profilePage = new ProfilePage();

        LoginResponseModel response = loginApi.login(creds);

        IsbnModel isbnModel = new IsbnModel();
        isbnModel.setIsbn(bookId);

        List<IsbnModel> collectionIsbn = new ArrayList<>();
        collectionIsbn.add(isbnModel);

        AddingBookToListModel booksList = new AddingBookToListModel();
        booksList.setUserId(response.getUserId());
        booksList.setCollectionOfIsbns(collectionIsbn);

        RemovingBookFromListModel removingBooksList = new RemovingBookFromListModel();
        removingBooksList.setIsbn(bookId);
        removingBooksList.setUserId(response.getUserId());

        bookApi.removeAllBooks(response);
        bookApi.addBook(response, booksList);
        bookApi.removeBook(response, removingBooksList);

        step("Добавление куков на сайт", () -> {
            profilePage.addCookies(response);
        });

        step("Открытие страницы профиля", () -> {
            profilePage.openProfilePage();
        });

        step("Проверка удаленной из списка книги", () -> {
            profilePage.checkEmptyTable();
        });
    }
}
