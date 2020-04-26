package listChannels;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListChannelsResponsePOJO {
    @JsonProperty("ok")
    private Boolean ok;
    @JsonProperty("channels")
    private List<Channels> channels;
    @JsonProperty("error")
    private String error;
    @JsonProperty("response_metadata")
    private ResponseMetadata response_metadata;

    @JsonProperty("ok")
    public Boolean getOk() { return ok; }
    @JsonProperty("ok")
    public void setOk(Boolean ok) { this.ok = ok; }
    @JsonProperty("channels")
    public List<Channels> getChannels ()
    {
        return channels;
    }
    @JsonProperty("channels")
    public void setChannels (List<Channels> channels)
    {
        this.channels = channels;
    }
    @JsonProperty("error")
    public String getError () { return error; }
    @JsonProperty("error")
    public void setError (String error) { this.error = error; }
}
