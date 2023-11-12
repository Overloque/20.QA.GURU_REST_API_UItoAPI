package qa.demo.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import qa.demo.api.BookApi;
import qa.demo.models.authorization.LoginResponseModel;
import qa.demo.models.books.AddingBookToListModel;
import qa.demo.models.books.GetBookModel;
import qa.demo.models.books.IsbnModel;
import qa.demo.models.books.RemovingBookFromListModel;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.assertj.core.api.Assertions.assertThat;
import static qa.demo.utils.TestDataVariables.*;
import static qa.demo.utils.TestDataVariables.bookId;

@Epic(value = "Проверка апи сайта https://demoqa.com")
@Feature(value = "Проверка методов, связанных с магазином книг")
@Link(value = "demoqa", url = "https://demoqa.com")
@Owner("Overloque")
@Tag("bookstore")
public class BookStoreTest extends BaseTest {
    BookApi bookApi = new BookApi();

    @Test
    @Severity(NORMAL)
    @DisplayName("Проверка удаления книги из списка")
    void checkSuccessRemovingBookTest() {
        step("Вызов метода для генерации токена пользователя", () ->
                authorizationApi.generateToken(existCreds));

        final LoginResponseModel loginResponse = step("Вызов метода авторизации", () ->
                authorizationApi.login(existCreds));

        step("Подготовка данных для действий по удалению/добавлению книги", () -> {
            IsbnModel isbnModel = new IsbnModel(bookId);

            List<IsbnModel> collectionIsbn = new ArrayList<>();
            collectionIsbn.add(isbnModel);

            AddingBookToListModel booksList = new AddingBookToListModel(loginResponse.getUserId(), collectionIsbn);

            RemovingBookFromListModel removingBooksList = new RemovingBookFromListModel(bookId, loginResponse.getUserId());

            step("Вызов метода для удаления всех книг", () -> {
                bookApi.removeAllBooks(loginResponse);
            });

            step("Вызов метода для удаления всех книг", () -> {
                bookApi.addBook(loginResponse, booksList);
            });

            step("Вызов метода удаления одной книги", () -> {
                bookApi.removeBook(loginResponse, removingBooksList);
            });
        });
    }

    @Test
    @Severity(NORMAL)
    @DisplayName("Проверка полей на странице книги")
    void checkBookFieldsTest() {
        step("Вызов метода для генерации токена пользователя", () ->
                authorizationApi.generateToken(existCreds));

        step("Вызов метода авторизации", () ->
                authorizationApi.login(existCreds));

        final GetBookModel getBookResponse = step("Вызов метода получения книги", () ->
                bookApi.getBook(bookId));

        step("Вызов метода получения книги", () -> {
            step("Проверка полей у книги", () -> {
                assertThat(getBookResponse.getAuthor())
                        .as("Автор книги")
                        .isEqualTo("Richard E. Silverman");

                assertThat(getBookResponse.getPublishDate())
                        .as("Дата публикации книги")
                        .isEqualTo("2020-06-04T08:48:39.000Z");

                assertThat(getBookResponse.getPublisher())
                        .as("Издатель")
                        .isEqualTo("O'Reilly Media");

                assertThat(getBookResponse.getTitle())
                        .as("Название книги")
                        .isEqualTo("Git Pocket Guide");

                assertThat(getBookResponse.getWebsite())
                        .as("Вебсайт")
                        .isEqualTo("http://chimera.labs.oreilly.com/books/1230000000561/index.html");
            });
        });
    }
}
