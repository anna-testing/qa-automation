package portfolio.aqa;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import portfolio.aqa.config.BrowserConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CucumberHooks {
    @Before
    public void initializeWebDriver() {
        BrowserConfiguration.apply();
    }

    @After
    public void closeWebDriver(Scenario scenario) throws IOException {
        if (scenario.isFailed() && WebDriverRunner.hasWebDriverStarted()) {
            File screenshot = Screenshots.takeScreenShotAsFile();
            if (screenshot != null) {
                scenario.attach(Files.readAllBytes(screenshot.toPath()), "image/png", "Ошибка UI-теста");
            }
        }
        Selenide.closeWebDriver();
    }
}

