package qa.demo.api;

import qa.demo.models.AddingBookToListModel;
import qa.demo.models.LoginResponseModel;
import qa.demo.models.RemovingBookFromListModel;

import static io.restassured.RestAssured.given;
import static qa.demo.specs.BookSpec.*;

public class BookApi {
    public void addBook(LoginResponseModel response, AddingBookToListModel booksList) {
        given(commonBookSpec)
                .header("Authorization", "Bearer " + response.getToken())
                .body(booksList)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(addBookResponseSpec);
    }

    public void removeBook(LoginResponseModel response, RemovingBookFromListModel book) {
        given(commonBookSpec)
                .header("Authorization", "Bearer " + response.getToken())
                .queryParam("UserId", response.getUserId())
                .body(book)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(removingBookResponseSpec);
    }
}
