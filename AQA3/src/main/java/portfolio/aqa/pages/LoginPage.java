package portfolio.aqa.pages;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

// ai-assisted-solution
public class LoginPage {
    public LoginPage openPage() {
        open("/");
        $("#login-button").shouldBe(visible.because("кнопка входа должна отображаться"));
        return this;
    }

    public void loginAs(String username, String password) {
        $("#user-name").setValue(username);
        $("#password").setValue(password);
        $("#login-button").click();
    }

    public void shouldShowError(String expectedMessage) {
        $("[data-test='error']")
                .shouldBe(visible.because("ошибка авторизации должна отображаться"))
                .shouldHave(exactText(expectedMessage)
                        .because("текст ошибки авторизации должен быть понятен пользователю"));
    }
}

