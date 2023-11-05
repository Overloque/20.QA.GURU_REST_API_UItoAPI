package qa.demo.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import qa.demo.tests.BaseTest;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static qa.demo.helpers.CustomAllureListener.withCustomTemplates;

public class BookSpec extends BaseTest {
    public static RequestSpecification commonBookSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .log().method()
            .contentType(JSON)
            .baseUri(config.getBaseUri())
            .basePath(config.getBasePathBookStore());

    public static ResponseSpecification addBookResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(201)
            .build();

    public static ResponseSpecification removingBookResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(204)
            .build();

    public static ResponseSpecification getBookResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .build();
}
