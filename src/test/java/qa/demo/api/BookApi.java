package qa.demo.api;

import qa.demo.models.books.AddingBookToListModel;
import qa.demo.models.authorization.LoginResponseModel;
import qa.demo.models.books.GetBookModel;
import qa.demo.models.books.RemovingBookFromListModel;
import qa.demo.utils.TestDataVariables;

import static io.restassured.RestAssured.given;
import static qa.demo.specs.BookSpec.*;

public class BookApi {
    public void addBook(LoginResponseModel response, AddingBookToListModel booksList) {
        given(commonBookSpec)
                .header("Authorization", "Bearer " + response.getToken())
                .body(booksList)
                .when()
                .post("/Books")
                .then()
                .spec(addBookResponseSpec);
    }

    public void removeBook(LoginResponseModel response, RemovingBookFromListModel book) {
        given(commonBookSpec)
                .header("Authorization", "Bearer " + response.getToken())
                .body(book)
                .when()
                .delete("/Book")
                .then()
                .spec(removingBookResponseSpec);
    }

    public void removeAllBooks(LoginResponseModel response) {
        given(commonBookSpec)
                .header("Authorization", "Bearer " + response.getToken())
                .queryParam("UserId", response.getUserId())
                .when()
                .delete("/Books")
                .then()
                .spec(removingBookResponseSpec);
    }

    public GetBookModel getBook(String isbn) {
        return given(commonBookSpec)
                .queryParam("ISBN", isbn)
                .when()
                .get("/Book")
                .then()
                .spec(getBookResponseSpec)
                .extract().as(GetBookModel.class);
    }
}
