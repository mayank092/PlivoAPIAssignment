package listChannels;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMetadata {

    @JsonProperty("next_cursor")
    private String next_cursor;
}
