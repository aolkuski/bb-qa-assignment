
package com.backbase.bblog.dataobjects;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "username",
        "image",
        "following"
})
@Generated("jsonschema2pojo")
public class Author {

    @JsonProperty("username")
    private String username;
    @JsonProperty("image")
    private String imageUrl;
    @JsonProperty("following")
    private Boolean following;

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    public Author withUsername(String username) {
        this.username = username;
        return this;
    }

    @JsonProperty("image")
    public String getImage() {
        return imageUrl;
    }

    @JsonProperty("image")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Author withImage(String image) {
        this.imageUrl = image;
        return this;
    }

    @JsonProperty("following")
    public Boolean getFollowing() {
        return following;
    }

    @JsonProperty("following")
    public void setFollowing(Boolean following) {
        this.following = following;
    }

    public Author withFollowing(Boolean following) {
        this.following = following;
        return this;
    }

}
