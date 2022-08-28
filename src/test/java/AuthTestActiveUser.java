import com.codeborne.selenide.Configuration;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AuthTestActiveUser {

    static LoginPage request = new LoginPage();
    static RegistrationInfo info = DataGenerator.Registration.registrationInfo("en", "active");
    RegistrationInfo wrongInfo = DataGenerator.Registration.registrationWrongInfo("en", "active");

    @BeforeAll
    static void setUpAll() {
        RequestSpecification requestSpec = Request.requestSpec("http://localhost", 9999, ContentType.JSON, ContentType.JSON, LogDetail.ALL);
        Request.post(requestSpec, info, "/api/system/users", 200);
    }

    @BeforeEach
    public void openPage() {request.openPage();}


    @Test
    public void shouldSuccessTest() {
        Configuration.holdBrowserOpen=true;
        request.login(info.getLogin());
        request.password(info.getPassword());
        request.click();
        request.account(3);
    }

    @Test
    public void shouldEmptyLoginTest() {
        Configuration.holdBrowserOpen=true;
        request.login("");
        request.password(info.getPassword());
        request.click();
        request.loginFieldFiling();
    }

    @Test
    public void shouldEmptyPasswordTest() {
        Configuration.holdBrowserOpen=true;
        request.login(info.getLogin());
        request.password("");
        request.click();
        request.passwordFieldFiling();
    }

    @Test
    public void shouldInvalidLoginTest() {
        Configuration.holdBrowserOpen=true;
        request.login(wrongInfo.getLogin());
        request.password(info.getPassword());
        request.click();
        request.error();
    }

    @Test
    public void shouldInvalidPasswordTest() {
        Configuration.holdBrowserOpen=true;
        request.login(info.getLogin());
        request.password(wrongInfo.getPassword());
        request.click();
        request.error();
    }
}
