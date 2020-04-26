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
    @JsonProperty("already_in_channel")
    private Boolean already_in_channel;


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
    @JsonProperty("already_in_channel")
    public Boolean getAlready_In_Channel () { return already_in_channel; }
    @JsonProperty("already_in_channel")
    public void setAlready_In_Channel (Boolean already_in_channel) { this.already_in_channel = already_in_channel; }
}
