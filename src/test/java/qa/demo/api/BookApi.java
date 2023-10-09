package qa.demo.api;

import io.qameta.allure.Step;
import qa.demo.models.books.AddingBookToListModel;
import qa.demo.models.authorization.LoginResponseModel;
import qa.demo.models.books.RemovingBookFromListModel;

import static io.restassured.RestAssured.given;
import static qa.demo.specs.BookSpec.*;

public class BookApi {
    @Step("Запрос на добавление книги в список")
    public void addBook(LoginResponseModel response, AddingBookToListModel booksList) {
        given(commonBookSpec)
                .header("Authorization", "Bearer " + response.getToken())
                .body(booksList)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(addBookResponseSpec);
    }

    @Step("Запрос на удаление одной книги из списка")
    public void removeBook(LoginResponseModel response, RemovingBookFromListModel book) {
        given(commonBookSpec)
                .header("Authorization", "Bearer " + response.getToken())
                .body(book)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(removingBookResponseSpec);
    }

    @Step("Запрос на удаление всех книг из списка")
    public void removeAllBooks(LoginResponseModel response) {
        given(commonBookSpec)
                .header("Authorization", "Bearer " + response.getToken())
                .queryParam("UserId", response.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(removingBookResponseSpec);
    }
}
