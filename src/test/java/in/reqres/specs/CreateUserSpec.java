package in.reqres.specs;

import in.reqres.tests.BaseTest;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class CreateUserSpec extends BaseTest {
    public static RequestSpecification createUserRequestSpec = with()
            .log().uri()
            .log().method()
            .log().body()
            .contentType(JSON)
            .baseUri(config.baseUrl())
            .basePath(config.basePath());

    public static ResponseSpecification createUserResponseSuccessSpec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(201)
            .build();
}
