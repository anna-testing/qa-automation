package portfolio.api.retrofit;

/* manual-solution :
public interface ManualReqresService {
    @GET("users/{id}") User getUser(int id);             // отсутствуют Call и @Path
    @POST("users") User createUser(User user);           // отсутствует @Body
    @POST("users/{id}") User updateUser(int id, User u); // указан неверный HTTP-метод
    @DELETE("users/{id}") User deleteUser(int id);       // указан неверный тип ответа
}
*/
