package portfolio.api.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import okhttp3.ResponseBody;
import portfolio.api.dto.SingleUserResponse;
import portfolio.api.dto.RegistrationRequest;
import portfolio.api.dto.UserMutationResponse;
import portfolio.api.dto.UserRequest;

// ai-assisted-solution: вариант после проверки
public interface ReqresService {
    @GET("users/{id}")
    Call<SingleUserResponse> getUser(@Path("id") int id);

    @POST("users")
    Call<UserMutationResponse> createUser(@Body UserRequest user);

    @POST("register")
    Call<ResponseBody> register(@Body RegistrationRequest credentials);

    @PUT("users/{id}")
    Call<UserMutationResponse> updateUser(@Path("id") int id, @Body UserRequest user);

    @DELETE("users/{id}")
    Call<Void> deleteUser(@Path("id") int id);
}
