package response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsePOJO {
    @JsonProperty("ok")
    private Boolean ok;
    @JsonProperty("channel")
    private Channel channel;
    @JsonProperty("error")
    private String error;
    @JsonProperty("detail")
    private String detail;


    @JsonProperty("ok")
    public Boolean getOk() { return ok; }
    @JsonProperty("ok")
    public void setOk(Boolean ok) { this.ok = ok; }
    @JsonProperty("channel")
    public Channel getChannel ()
    {
        return channel;
    }
    @JsonProperty("channel")
    public void setChannel (Channel channel)
    {
        this.channel = channel;
    }
    @JsonProperty("error")
    public String getError () { return error; }
    @JsonProperty("error")
    public void setError (String error) { this.error = error; }
    @JsonProperty("detail")
    public String getDetail () { return detail; }
    @JsonProperty("detail")
    public void setDetail (String detail) { this.detail = detail; }
}
