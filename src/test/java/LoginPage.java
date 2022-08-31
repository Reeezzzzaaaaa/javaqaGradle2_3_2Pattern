import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    public void openPage() {
        open("http://localhost:9999/");
    }
    //public void login(String login) {$x("//*[@data-test-id='login']//input").setValue(login);}
    //public void password(String password) {$x("//*[@data-test-id='password']//input").setValue(password);}
    public void account(int seconds) {$x("//*[@id='root']//*[contains(text(), 'Личный кабинет')]").shouldBe(visible, Duration.ofSeconds(seconds));}
    //public void click() {$x("//*[@data-test-id='action-login']").click();}
    public void authorization(String login, String password) {
        $x("//*[@data-test-id='login']//input").setValue(login);
        $x("//*[@data-test-id='password']//input").setValue(password);
        $x("//*[@data-test-id='action-login']").click();
    }
    public void loginFieldFiling() {$x("//*[@data-test-id='login']//*[contains(text(), 'Поле обязательно')]").shouldBe(visible);}
    public void passwordFieldFiling() {$x("//*[@data-test-id='password']//*[contains(text(), 'Поле обязательно')]").shouldBe(visible);}
    public void error() {$x("//*[@data-test-id='error-notification']//*[contains(text(), 'Ошибка')]").shouldBe(visible);}

    //public LoginPage dataInput(String login, String password) {
    //    login(login);
    //    password(password);
    //    click();
    //    return new LoginPage();
    //}
}
