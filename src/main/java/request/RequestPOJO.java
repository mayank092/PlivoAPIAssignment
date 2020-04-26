package request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestPOJO {

    @JsonProperty("channel")
    private String channel;
    @JsonProperty("name")
    private String name;
    @JsonProperty("limit")
    private Integer limit;

    @JsonProperty("channel")
    public String getChannel() { return channel; }
    @JsonProperty("channel")
    public void setChannel(String channel) { this.channel = channel; }
    @JsonProperty("name")
    public String getName() {
        return name;
    }
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }
    @JsonProperty("limit")
    public Integer getLimit() {
        return limit;
    }
    @JsonProperty("limit")
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}