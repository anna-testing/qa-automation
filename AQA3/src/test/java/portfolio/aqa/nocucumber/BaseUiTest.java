package portfolio.aqa.nocucumber;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import portfolio.aqa.config.BrowserConfiguration;

abstract class BaseUiTest {
    @BeforeAll
    static void configureBrowser() {
        BrowserConfiguration.apply();
    }

    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }
}

