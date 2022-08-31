import com.codeborne.selenide.Configuration;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AuthTestActiveUser {

    @BeforeEach
    public void openPage() {request.openPage();}

    static LoginPage request = new LoginPage();
    static RegistrationInfo info = DataGenerator.Registration.registrationInfo("en", "active");
    static RegistrationInfo wrongInfo = DataGenerator.Registration.registrationWrongInfo("en", "active");

    @BeforeAll
    static void setUpAll() {
        Request.post(Request.requestSpec(), info, "/api/system/users", 200);
    }

    @Test
    public void shouldSuccessTest() {
        Configuration.holdBrowserOpen=true;
        request.authorization(info.getLogin(), info.getPassword());
        request.account(3);
    }

    @Test
    public void shouldEmptyLoginTest() {
        Configuration.holdBrowserOpen=true;
        request.authorization("", info.getPassword());
        request.loginFieldFiling();
    }

    @Test
    public void shouldEmptyPasswordTest() {
        Configuration.holdBrowserOpen=true;
        request.authorization(info.getLogin(), "");
        request.passwordFieldFiling();
    }

    @Test
    public void shouldInvalidLoginTest() {
        Configuration.holdBrowserOpen=true;
        request.authorization(wrongInfo.getLogin(), info.getPassword());
        request.error();
    }

    @Test
    public void shouldInvalidPasswordTest() {
        Configuration.holdBrowserOpen=true;
        request.authorization(info.getLogin(), wrongInfo.getPassword());
        request.error();
    }
}
