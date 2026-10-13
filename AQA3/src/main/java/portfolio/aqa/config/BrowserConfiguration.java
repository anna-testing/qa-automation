package portfolio.aqa.config;

import com.codeborne.selenide.Configuration;

public final class BrowserConfiguration {
    private BrowserConfiguration() {
    }

    public static void apply() {
        Configuration.browser = TestConfig.browser();
        Configuration.browserSize = TestConfig.browserSize();
        Configuration.headless = TestConfig.headless();
        Configuration.timeout = TestConfig.timeout();
        Configuration.baseUrl = TestConfig.testUrl();
        Configuration.pageLoadStrategy = "eager";
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
    }
}

