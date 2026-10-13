package portfolio.api.config;

public final class ApiConfig {
    public static final String BASE_URL = "https://reqres.in/api/";
    public static final String API_KEY = System.getenv().getOrDefault("REQRES_API_KEY", "reqres-free-v1");

    private ApiConfig() {
    }
}
