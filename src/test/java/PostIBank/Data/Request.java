package PostIBank.Data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;

import static io.restassured.RestAssured.given;

@UtilityClass
public class Request {

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(9999)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static void post(RequestSpecification spec, Object body, String path, int statusCode) {
        given()
                .spec(spec)
                .body(body)
                .when()
                .post(path)
                .then()
                .statusCode(statusCode);
    }
}
