package response;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Channel {

    @JsonProperty("priority")
    private Object priority;
    @JsonProperty("last_read")
    private String last_read;
    @JsonProperty("latest")
    private String latest;
    @JsonProperty("unread_count")
    private Integer unread_count;
    @JsonProperty("unread_count_display")
    private Integer unread_count_display;
    @JsonProperty("topic")
    private Topic topic;
    @JsonProperty("purpose")
    private Purpose purpose;
    @JsonProperty("name_normalized")
    private String name_normalized;
    @JsonProperty("created")
    private Object created;
    @JsonProperty("is_general")
    private Boolean is_general;
    @JsonProperty("unlinked")
    private String unlinked;
    @JsonProperty("is_org_shared")
    private Boolean is_org_shared;
    @JsonProperty("is_mpim")
    private Boolean is_mpim;
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("is_channel")
    private Boolean is_channel;
    @JsonProperty("creator")
    private String creator;
    @JsonProperty("is_archived")
    private Boolean is_archived;
    @JsonProperty("is_shared")
    private Boolean is_shared;
    @JsonProperty("is_member")
    private Boolean is_member;
    @JsonProperty("is_private")
    private Boolean is_private;
    @JsonProperty("members")
    private String[] members;
    @JsonProperty("previous_names")
    private String[] previous_names;

    @JsonProperty("id")
    public String getId() { return id; }
    @JsonProperty("id")
    public void setId(String id) { this.id = id; }
    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String name) { this.name = name; }
    @JsonProperty("is_channel")
    public Boolean getIs_Channel() { return is_channel; }
    @JsonProperty("is_channel")
    public void setIs_Channel(Boolean is_channel) { this.is_channel = is_channel; }
    @JsonProperty("creator")
    public String getCreator() { return creator; }
    @JsonProperty("creator")
    public void setCreator(String creator) { this.creator = creator; }
    @JsonProperty("is_archived")
    public Boolean getIs_Archived() { return is_archived; }
    @JsonProperty("is_archived")
    public void setIs_Archived(Boolean is_archived) { this.is_archived = is_archived; }
    @JsonProperty("is_shared")
    public Boolean getIs_Shared() { return is_shared; }
    @JsonProperty("is_shared")
    public void setIs_Shared(Boolean is_shared) { this.is_shared = is_shared; }
    @JsonProperty("is_member")
    public Boolean getIs_Member() { return is_member; }
    @JsonProperty("is_member")
    public void setIs_Member(Boolean is_member) { this.is_member = is_member; }
    @JsonProperty("is_private")
    public Boolean getIs_Private() { return is_private; }
    @JsonProperty("is_private")
    public void setIs_Private(Boolean is_private) { this.is_private = is_private; }
    @JsonProperty("members")
    public String[] getMembers ()
    {
        return members;
    }
    @JsonProperty("members")
    public void setMembers (String[] members)
    {
        this.members = members;
    }
    @JsonProperty("previous_names")
    public String[] getPreviousNames ()
    {
        return previous_names;
    }
    @JsonProperty("previous_names")
    public void setPreviousNames (String[] previous_names)
    {
        this.previous_names = previous_names;
    }
}
