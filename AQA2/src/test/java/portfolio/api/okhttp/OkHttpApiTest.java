package portfolio.api.okhttp;

import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import portfolio.api.dto.SingleUserResponse;
import portfolio.api.dto.UserMutationResponse;

import static org.assertj.core.api.Assertions.assertThat;

class OkHttpApiTest extends OkHttpTestBase {
    private static final MediaType JSON = MediaType.get("application/json");

    @Test @DisplayName("GET: существующий пользователь возвращается с корректными данными")
    void getExistingUser() throws Exception {
        try (Response response = execute(request("users/2").get().build())) {
            assertSuccessful(response);
            SingleUserResponse payload = mapper.readValue(response.body().string(), SingleUserResponse.class);
            assertThat(payload.data().getId()).as("ID пользователя должен совпадать с запрошенным").isEqualTo(2);
        }
    }

    @Test @DisplayName("GET: отсутствующий пользователь возвращает 404")
    void getMissingUser() throws Exception {
        try (Response response = execute(request("users/23").get().build())) {
            assertThat(response.code()).as("Для отсутствующего пользователя ожидается 404").isEqualTo(404);
        }
    }

    @Test @DisplayName("POST: пользователь создаётся из JSON-строки")
    void createUserFromJsonString() throws Exception {
        String json = "{\"name\":\"Anna\",\"job\":\"AQA\"}";
        try (Response response = execute(request("users").post(RequestBody.create(json, JSON)).build())) {
            assertThat(response.code()).as("Создание должно вернуть 201").isEqualTo(201);
            UserMutationResponse payload = mapper.readValue(response.body().string(), UserMutationResponse.class);
            assertThat(payload.name()).as("Имя должно быть отражено в ответе").isEqualTo("Anna");
        }
    }

    @Test @DisplayName("POST: регистрация без пароля возвращает 400")
    void registerWithoutPassword() throws Exception {
        String json = "{\"email\":\"sydney@fife\"}";
        try (Response response = execute(request("register").post(RequestBody.create(json, JSON)).build())) {
            assertThat(response.code()).as("Без пароля регистрация должна вернуть 400").isEqualTo(400);
        }
    }

    @Test @DisplayName("PUT: пользователь обновляется JSON-объектом, собранным по полям")
    void updateUserFromObjectNode() throws Exception {
        ObjectNode json = mapper.createObjectNode();
        json.put("name", "Anna Updated");
        json.put("job", "Senior AQA");
        try (Response response = execute(request("users/2").put(RequestBody.create(mapper.writeValueAsString(json), JSON)).build())) {
            assertSuccessful(response);
            UserMutationResponse payload = mapper.readValue(response.body().string(), UserMutationResponse.class);
            assertThat(payload.job()).as("Должна вернуться обновлённая должность").isEqualTo("Senior AQA");
        }
    }

    @Test @DisplayName("PUT: запрос с неверным API-ключом отклоняется")
    void updateWithInvalidKey() throws Exception {
        Request request = request("users/2").header("x-api-key", "invalid")
                .put(RequestBody.create("{}", JSON)).build();
        try (Response response = execute(request)) {
            assertThat(response.code()).as("Неверный ключ должен быть отклонён").isIn(401, 403);
        }
    }

    @Test @DisplayName("DELETE: существующий пользователь удаляется без тела ответа")
    void deleteUser() throws Exception {
        try (Response response = execute(request("users/2").delete().build())) {
            assertThat(response.code()).as("Удаление должно вернуть 204").isEqualTo(204);
            assertThat(response.body().string()).as("Ответ DELETE должен быть пустым").isEmpty();
        }
    }

    @Test @DisplayName("DELETE: запрос с неверным API-ключом отклоняется")
    void deleteWithInvalidKey() throws Exception {
        Request request = request("users/2").header("x-api-key", "invalid").delete().build();
        try (Response response = execute(request)) {
            assertThat(response.code()).as("Неверный ключ должен быть отклонён").isIn(401, 403);
        }
    }
}
