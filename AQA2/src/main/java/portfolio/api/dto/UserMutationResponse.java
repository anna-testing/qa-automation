package portfolio.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserMutationResponse(String name, String job, String id, String createdAt, String updatedAt) {
}
