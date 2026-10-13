package portfolio.api.retrofit;

import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import portfolio.api.config.ApiConfig;

public final class RetrofitFactory {
    private RetrofitFactory() {
    }

    public static ReqresService create() {
        return create(ApiConfig.API_KEY);
    }

    public static ReqresService create(String apiKey) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> chain.proceed(chain.request().newBuilder()
                        .header("x-api-key", apiKey).build()))
                .build();
        return new Retrofit.Builder()
                .baseUrl(ApiConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                .build()
                .create(ReqresService.class);
    }
}
