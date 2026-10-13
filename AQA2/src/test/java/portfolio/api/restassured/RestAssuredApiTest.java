package portfolio.api.restassured;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import portfolio.api.dto.UserMutationResponse;
import portfolio.api.dto.UserRequest;
import portfolio.api.record.SingleUserRecordResponse;

import static org.assertj.core.api.Assertions.assertThat;

class RestAssuredApiTest extends RestAssuredTestBase {
    @Test @DisplayName("GET REST Assured: пользователь 7 десериализуется в record")
    void getUser() {
        SingleUserRecordResponse result = request().get("users/7").then().statusCode(200)
                .extract().as(SingleUserRecordResponse.class);
        assertThat(result.data().firstName()).as("Имя пользователя 7 должно совпадать").isEqualTo("Michael");
    }

    @Test @DisplayName("GET REST Assured: пользователь 99 отсутствует")
    void getMissingUser() { request().get("users/99").then().statusCode(404); }

    @Test @DisplayName("POST REST Assured: создаёт пользователя из record")
    void createUser() {
        UserMutationResponse result = request().body(new UserRequest("Boris", "QA"))
                .post("users").then().statusCode(201).extract().as(UserMutationResponse.class);
        assertThat(result.name()).as("Ответ должен содержать отправленное имя").isEqualTo("Boris");
    }

    @Test @DisplayName("POST REST Assured: регистрация без email отклоняется")
    void invalidRegistration() { request().body("{\"password\":\"pistol\"}").post("register").then().statusCode(400); }

    @Test @DisplayName("PUT REST Assured: обновляет должность пользователя 3")
    void updateUser() {
        UserMutationResponse result = request().body(new UserRequest("Emma", "Lead QA"))
                .put("users/3").then().statusCode(200).extract().as(UserMutationResponse.class);
        assertThat(result.job()).as("Ответ должен содержать новую должность").isEqualTo("Lead QA");
    }

    @Test @DisplayName("PUT REST Assured: неверный API-ключ отклоняется")
    void invalidUpdate() { request().header("x-api-key", "invalid").body("{}").put("users/3").then().statusCode(anyOf401Or403()); }

    @Test @DisplayName("DELETE REST Assured: удаление пользователя 4 возвращает 204")
    void deleteUser() { request().delete("users/4").then().statusCode(204); }

    @Test @DisplayName("DELETE REST Assured: неверный API-ключ отклоняется")
    void invalidDelete() { request().header("x-api-key", "invalid").delete("users/4").then().statusCode(anyOf401Or403()); }

    private org.hamcrest.Matcher<Integer> anyOf401Or403() {
        return org.hamcrest.Matchers.anyOf(org.hamcrest.Matchers.is(401), org.hamcrest.Matchers.is(403));
    }
}
