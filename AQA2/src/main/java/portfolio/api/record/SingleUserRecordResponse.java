package portfolio.api.record;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SingleUserRecordResponse(UserRecord data) {
}
