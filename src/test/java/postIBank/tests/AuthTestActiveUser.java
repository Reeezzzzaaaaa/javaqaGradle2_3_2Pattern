package postIBank.tests;

import postIBank.data.DataGenerator;
import postIBank.data.RegistrationInfo;
import postIBank.data.Request;
import postIBank.page.LoginPage;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class AuthTestActiveUser {

    @BeforeEach
    public void openPage() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
    }

    static RegistrationInfo info = DataGenerator.Registration.registrationInfo("en", "active");

    @BeforeAll
    static void setUpAll() {
        Request.post(Request.requestSpec(), info, "/api/system/users", 200);
    }

    @Test
    public void shouldSuccessTest() {
        LoginPage request = new LoginPage();
        open("http://localhost:9999/");
        request.authorization(info.getLogin(), info.getPassword());
        request.account(3);
    }

    @Test
    public void shouldEmptyLoginTest() {
        LoginPage request = new LoginPage();
        request.authorization("", info.getPassword());
        request.loginFieldFiling();
    }

    @Test
    public void shouldEmptyPasswordTest() {
        LoginPage request = new LoginPage();
        request.authorization(info.getLogin(), "");
        request.passwordFieldFiling();
    }

    @Test
    public void shouldInvalidLoginTest() {
        LoginPage request = new LoginPage();
        request.authorization(info.getLogin(), info.getLogin());
        request.error();
    }

    @Test
    public void shouldInvalidPasswordTest() {
        LoginPage request = new LoginPage();
        request.authorization(info.getPassword(), info.getPassword());
        request.error();
    }
}
