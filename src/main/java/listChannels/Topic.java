package listChannels;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Topic {

    @JsonProperty("value")
    private String value;
    @JsonProperty("creator")
    private String creator;
    @JsonProperty("last_set")
    private Integer last_set;
}
