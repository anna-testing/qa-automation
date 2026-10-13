package portfolio.aqa.config;

public final class TestConfig {
    private static final String DEFAULT_URL = "https://www.saucedemo.com/";

    private TestConfig() {
    }

    public static String browser() {
        return System.getProperty("browser", "chrome");
    }

    public static String browserSize() {
        return System.getProperty("browserSize", "1600x900");
    }

    public static boolean headless() {
        return Boolean.parseBoolean(System.getProperty("headless", "false"));
    }

    public static long timeout() {
        return Long.parseLong(System.getProperty("timeout", "10000"));
    }

    public static String testUrl() {
        return System.getProperty("testUrl", DEFAULT_URL);
    }
}

