
package com.backbase.bblog.dataobjects;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "slug",
        "title",
        "description",
        "body",
        "createdAt",
        "updatedAt",
        "tagList",
        "favorited",
        "favoritesCount",
        "author"
})
@Generated("jsonschema2pojo")
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

    @JsonProperty("slug")
    public String getSlug() {
        return slug;
    }

    @JsonProperty("slug")
    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Article withSlug(String slug) {
        this.slug = slug;
        return this;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    public Article withTitle(String title) {
        this.title = title;
        return this;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Article withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    public Article withBody(String body) {
        this.body = body;
        return this;
    }

    @JsonProperty("createdAt")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Article withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @JsonProperty("updatedAt")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updatedAt")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Article withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @JsonProperty("tagList")
    public List<String> getTagList() {
        return tagList;
    }

    @JsonProperty("tagList")
    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public Article withTagList(List<String> tagList) {
        this.tagList = tagList;
        return this;
    }

    @JsonProperty("favorited")
    public Boolean getFavorited() {
        return favorited;
    }

    @JsonProperty("favorited")
    public void setFavorited(Boolean favorited) {
        this.favorited = favorited;
    }

    public Article withFavorited(Boolean favorited) {
        this.favorited = favorited;
        return this;
    }

    @JsonProperty("favoritesCount")
    public Integer getFavoritesCount() {
        return favoritesCount;
    }

    @JsonProperty("favoritesCount")
    public void setFavoritesCount(Integer favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    public Article withFavoritesCount(Integer favoritesCount) {
        this.favoritesCount = favoritesCount;
        return this;
    }

    @JsonProperty("author")
    public Author getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(Author author) {
        this.author = author;
    }

    public Article withAuthor(Author author) {
        this.author = author;
        return this;
    }

}
