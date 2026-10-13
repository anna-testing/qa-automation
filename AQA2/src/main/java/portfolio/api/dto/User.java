package portfolio.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// ai-assisted-solution: вариант проверен по фактическому контракту ответа API.
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private Integer id;
    private String email;
    @JsonProperty("first_name")
    @SerializedName("first_name")
    private String firstName;
    @JsonProperty("last_name")
    @SerializedName("last_name")
    private String lastName;
    private String avatar;
}
