package portfolio.api.retrofit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import portfolio.api.dto.SingleUserResponse;
import portfolio.api.dto.RegistrationRequest;
import portfolio.api.dto.UserMutationResponse;
import portfolio.api.dto.UserRequest;

import static org.assertj.core.api.Assertions.assertThat;

class RetrofitApiTest extends RetrofitTestBase {
    @Test @DisplayName("GET Retrofit: пользователь 8 десериализуется Gson")
    void getUser() throws Exception {
        Response<SingleUserResponse> response = service.getUser(8).execute();
        assertSuccessful(response);
        assertThat(response.body()).as("Успешный ответ должен содержать тело").isNotNull();
        assertThat(response.body().data().getLastName()).as("Фамилия пользователя 8 должна совпадать").isEqualTo("Ferguson");
    }

    @Test @DisplayName("GET Retrofit: пользователь 404 отсутствует")
    void getMissingUser() throws Exception {
        Response<SingleUserResponse> response = service.getUser(404).execute();
        assertThat(response.code()).as("Для отсутствующего пользователя ожидается 404").isEqualTo(404);
    }

    @Test @DisplayName("POST Retrofit: создаёт пользователя")
    void createUser() throws Exception {
        Response<UserMutationResponse> response = service.createUser(new UserRequest("Nina", "Automation QA")).execute();
        assertThat(response.code()).as("Создание должно вернуть 201").isEqualTo(201);
        assertThat(response.body()).as("Ответ создания должен содержать тело").isNotNull();
        assertThat(response.body().name()).as("Имя должно быть отражено в ответе").isEqualTo("Nina");
    }

    @Test @DisplayName("POST Retrofit: регистрация без пароля возвращает 400")
    void registerWithoutPassword() throws Exception {
        Response<okhttp3.ResponseBody> response = service
                .register(new RegistrationRequest("sydney@fife", null))
                .execute();
        assertThat(response.code())
                .as("Регистрация без обязательного пароля должна вернуть 400")
                .isEqualTo(400);
    }

    @Test @DisplayName("PUT Retrofit: обновляет пользователя 5")
    void updateUser() throws Exception {
        Response<UserMutationResponse> response = service.updateUser(5, new UserRequest("Nina", "Principal QA")).execute();
        assertSuccessful(response);
        assertThat(response.body()).as("Ответ обновления должен содержать тело").isNotNull();
        assertThat(response.body().job()).as("Новая должность должна совпадать").isEqualTo("Principal QA");
    }

    @Test @DisplayName("PUT Retrofit: неверный API-ключ отклоняется")
    void invalidUpdate() throws Exception {
        Response<UserMutationResponse> response = RetrofitFactory.create("invalid")
                .updateUser(5, new UserRequest("Nina", "QA")).execute();
        assertThat(response.code()).as("Неверный ключ должен быть отклонён").isIn(401, 403);
    }

    @Test @DisplayName("DELETE Retrofit: удаляет пользователя 6")
    void deleteUser() throws Exception {
        Response<Void> response = service.deleteUser(6).execute();
        assertThat(response.code()).as("Удаление должно вернуть 204").isEqualTo(204);
    }

    @Test @DisplayName("DELETE Retrofit: неверный API-ключ отклоняется")
    void invalidDelete() throws Exception {
        Response<Void> response = RetrofitFactory.create("invalid").deleteUser(6).execute();
        assertThat(response.code()).as("Неверный ключ должен быть отклонён").isIn(401, 403);
    }
}
