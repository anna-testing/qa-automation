package portfolio.api.record;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// ai-assisted-solution: удалён Lombok, исправлены числовой тип id и сопоставления Jackson.
@JsonIgnoreProperties(ignoreUnknown = true)
public record UserRecord(
        Integer id,
        String email,
        @JsonProperty("first_name") String firstName,
        @JsonProperty("last_name") String lastName,
        String avatar) {
}
