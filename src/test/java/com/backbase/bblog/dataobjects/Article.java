
package com.backbase.bblog.dataobjects;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Article {

    @JsonProperty("slug")
    private String slug;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("body")
    private String body;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("updatedAt")
    private String updatedAt;
    @JsonProperty("tagList")
    private List<String> tagList = new ArrayList<>();
    @JsonProperty("favorited")
    private Boolean favorited;
    @JsonProperty("favoritesCount")
    private Integer favoritesCount;
    @JsonProperty("author")
    private Author author;

}
