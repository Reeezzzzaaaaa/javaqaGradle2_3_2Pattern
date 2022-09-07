package postIBank.tests;

import lombok.var;
import postIBank.data.DataGenerator;

import postIBank.page.LoginPage;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class AuthTestUser {

    @BeforeEach
    public void openPage() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
    }

    @Test
    public void shouldSuccessRegisteredActiveTest() {
        LoginPage request = new LoginPage();
        var registeredUser = DataGenerator.getRegisteredUser("active");
        request.authorization(registeredUser.getLogin(), registeredUser.getPassword());
        request.account(3);
    }

    @Test
    public void shouldErrorNotRegisteredActiveTest() {
        LoginPage request = new LoginPage();
        var notRegisteredUser = DataGenerator.getUser("active");
        request.authorization(notRegisteredUser.getLogin(), notRegisteredUser.getPassword());
        request.error();
    }

    @Test
    public void shouldErrorBlockedUserTest() {
        var registeredUser = DataGenerator.getRegisteredUser("blocked");
        LoginPage request = new LoginPage();
        request.authorization(registeredUser.getLogin(), registeredUser.getPassword());
        request.error();
    }

    @Test
    public void shouldUnSuccessRegisteredActiveUserWithWrongLoginTest() {
        var registeredUser = DataGenerator.getRegisteredUser("active");
        LoginPage request = new LoginPage();
        request.authorization(DataGenerator.getRandomLogin(), registeredUser.getPassword());
        request.error();
    }

    @Test
    public void shouldUnSuccessRegisteredActiveUserWithWrongPasswordTest() {
        var registeredUser = DataGenerator.getRegisteredUser("active");
        LoginPage request = new LoginPage();
        request.authorization(registeredUser.getLogin(), DataGenerator.getRandomPassword());
        request.error();
    }
}
