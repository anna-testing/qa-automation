package portfolio.aqa.pages.nocucumber;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPageNoCucumber {
    /* manual-solution
       Первый мой вариант оставлен как антипример:
       private final SelenideElement login = $("input");
       private final SelenideElement password = $("input");
       private final SelenideElement button = $("button");
       public void doIt(String u, String p) { login.setValue(u); password.setValue(p); button.click(); }
       Проблемы: одинаковый нестабильный локатор для двух полей и невыразительное имя метода.
    */

    // ai-assisted-solution
    public LoginPageNoCucumber openPage() {
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
                .shouldBe(visible.because("сообщение об ошибке авторизации должно отображаться"))
                .shouldHave(exactText(expectedMessage)
                        .because("текст ошибки должен объяснять причину отказа во входе"));
    }
}

