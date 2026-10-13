package portfolio.api.okhttp;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.BeforeEach;
import portfolio.api.config.ApiConfig;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

abstract class OkHttpTestBase {
    protected OkHttpClient client;
    protected ObjectMapper mapper;

    @BeforeEach
    void setUpClient() {
        client = new OkHttpClient();
        mapper = new ObjectMapper();
    }

    protected Request.Builder request(String path) {
        return new Request.Builder().url(ApiConfig.BASE_URL + path).header("x-api-key", ApiConfig.API_KEY);
    }

    protected Response execute(Request request) throws IOException {
        return client.newCall(request).execute();
    }

    protected void assertSuccessful(Response response) {
        assertThat(response.isSuccessful())
                .as("Ожидался успешный HTTP-ответ, фактический код: %s", response.code()).isTrue();
    }
}
