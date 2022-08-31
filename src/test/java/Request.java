import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;

import static io.restassured.RestAssured.given;

@UtilityClass
public class Request {
    public static RequestSpecification requestSpec(String baseUri, int port, ContentType accept, ContentType contentType, LogDetail log) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setPort(port)
                .setAccept(accept)
                .setContentType(contentType)
                .log(log)
                .build();
    }
    static RegistrationInfo info = DataGenerator.Registration.registrationInfo("en", "active");
    //RegistrationInfo wrongInfo = DataGenerator.Registration.registrationWrongInfo("en", "active");

    public static void postActive(RequestSpecification spec, Object body, String path, int statusCode) {
        given() // "дано"
                .spec(Request.requestSpec("http://localhost", 9999, ContentType.JSON, ContentType.JSON, LogDetail.ALL)) // указываем, какую спецификацию используем
                .body(info) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }

    public static void postBlocked(RequestSpecification spec, Object body, String path, int statusCode) {
        given() // "дано"
                .spec(Request.requestSpec("http://localhost", 9999, ContentType.JSON, ContentType.JSON, LogDetail.ALL)) // указываем, какую спецификацию используем
                .body(info) // передаём в теле объект, который будет преобразован в JSON/////////////
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }
}
