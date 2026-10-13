package portfolio.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SingleUserResponse(User data, Support support) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Support(String url, String text) {
    }
}
