import PostIBank.Data.DataGenerator;
import PostIBank.Data.Request;
import PostIBank.Info.RegistrationInfo;
import PostIBank.Page.LoginPage;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AuthTestActiveUser {

    @BeforeEach
    public void openPage() {
        LoginPage request = new LoginPage();
        request.openPage();}

    static RegistrationInfo info = DataGenerator.Registration.registrationInfo("en", "active");
    static RegistrationInfo wrongInfo = DataGenerator.Registration.registrationWrongInfo("en", "active");

    @BeforeAll
    static void setUpAll() {
        Request.post(Request.requestSpec(), info, "/api/system/users", 200);
    }

    @Test
    public void shouldSuccessTest() {
        Configuration.holdBrowserOpen=true;
        LoginPage request = new LoginPage();
        request.authorization(info.getLogin(), info.getPassword());
        request.account(3);
    }

    @Test
    public void shouldEmptyLoginTest() {
        Configuration.holdBrowserOpen=true;
        LoginPage request = new LoginPage();
        request.authorization("", info.getPassword());
        request.loginFieldFiling();
    }

    @Test
    public void shouldEmptyPasswordTest() {
        Configuration.holdBrowserOpen=true;
        LoginPage request = new LoginPage();
        request.authorization(info.getLogin(), "");
        request.passwordFieldFiling();
    }

    @Test
    public void shouldInvalidLoginTest() {
        Configuration.holdBrowserOpen=true;
        LoginPage request = new LoginPage();
        request.authorization(wrongInfo.getLogin(), info.getPassword());
        request.error();
    }

    @Test
    public void shouldInvalidPasswordTest() {
        Configuration.holdBrowserOpen=true;
        LoginPage request = new LoginPage();
        request.authorization(info.getLogin(), wrongInfo.getPassword());
        request.error();
    }
}
