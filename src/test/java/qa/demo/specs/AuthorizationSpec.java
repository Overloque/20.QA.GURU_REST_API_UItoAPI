package qa.demo.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import qa.demo.tests.BaseTest;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;
import static qa.demo.helpers.CustomAllureListener.withCustomTemplates;

public class AuthorizationSpec extends BaseTest {
    public static RequestSpecification authorizationRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .log().method()
            .contentType(JSON)
            .baseUri(config.getBaseUri())
            .basePath(config.getBasePathAccount());

    public static ResponseSpecification loginResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification registerSuccessResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(201)
            .build();

    public static ResponseSpecification registerFailedResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(406)
            .build();
}
