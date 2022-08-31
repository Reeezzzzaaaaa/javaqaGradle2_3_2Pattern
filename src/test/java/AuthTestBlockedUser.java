import com.codeborne.selenide.Configuration;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AuthTestBlockedUser {

    @BeforeEach
    public void openPage() {request.openPage();}

    static LoginPage request = new LoginPage();
    static RegistrationInfo info = DataGenerator.Registration.registrationInfo("en", "blocked");
    //RegistrationInfo wrongInfo = DataGenerator.Registration.registrationWrongInfo("en", "active");


    @Test
    public void shouldBlockedUserTest() {
        Configuration.holdBrowserOpen=true;
        request.authorization(info.getLogin(), info.getPassword());
        request.error();
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
        request.authorization(info.getLogin(), info.getPassword());//
        request.error();
    }

    @Test
    public void shouldInvalidPasswordTest() {
        Configuration.holdBrowserOpen=true;
        request.authorization(info.getLogin(), info.getPassword());//
        request.error();
    }
}
