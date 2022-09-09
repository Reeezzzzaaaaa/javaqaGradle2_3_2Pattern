package postIBank.page;

import com.codeborne.selenide.Condition;
import postIBank.data.DataGenerator;
import postIBank.data.RegistrationDto;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    public void account(int seconds) {
        $x("//*[@id='root']//*[contains(text(), 'Личный кабинет')]").shouldBe(visible, Duration.ofSeconds(seconds));
    }

    public void authorization(String login, String password) {
        $x("//*[@data-test-id='login']//input").setValue(login);
        $x("//*[@data-test-id='password']//input").setValue(password);
        $x("//*[@data-test-id='action-login']").click();
    }

    public void loginFieldFiling() {
        $x("//*[@data-test-id='login']//*[contains(text(), 'Поле обязательно')]").shouldBe(visible);
    }

    public void passwordFieldFiling() {
        $x("//*[@data-test-id='password']//*[contains(text(), 'Поле обязательно')]").shouldBe(visible);
    }

    public void errorBlockedUser() {
        $x("//*[@data-test-id='error-notification']//*[@class='notification__content']")
                .shouldBe(visible)
                .shouldHave(Condition.text("Пользователь заблокирован"));
    }

    public void errorActiveUser() {
        $x("//*[@data-test-id='error-notification']//*[@class='notification__content']")
                .shouldBe(visible)
                .shouldHave(Condition.text("Неверно указан логин или пароль"));
    }
}
