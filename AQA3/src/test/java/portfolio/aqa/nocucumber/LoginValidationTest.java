package portfolio.aqa.nocucumber;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import portfolio.aqa.pages.nocucumber.LoginPageNoCucumber;

@DisplayName("Валидация авторизации без Cucumber")
class LoginValidationTest extends BaseUiTest {
    @Test
    @DisplayName("Система отклоняет авторизацию с неверным паролем")
    void userCannotLoginWithInvalidPassword() {
        LoginPageNoCucumber loginPage = new LoginPageNoCucumber();

        loginPage.openPage();
        loginPage.loginAs("standard_user", "wrong_password");
        loginPage.shouldShowError("Epic sadface: Username and password do not match any user in this service");
    }
}

