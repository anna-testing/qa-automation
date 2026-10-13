package portfolio.api.retrofit;

import org.junit.jupiter.api.BeforeEach;
import retrofit2.Response;

import static org.assertj.core.api.Assertions.assertThat;

abstract class RetrofitTestBase {
    protected ReqresService service;

    @BeforeEach
    void setUpRetrofit() {
        service = RetrofitFactory.create();
    }

    protected void assertSuccessful(Response<?> response) {
        assertThat(response.isSuccessful())
                .as("Ожидался успешный Retrofit-ответ, фактический код: %s", response.code()).isTrue();
    }
}
