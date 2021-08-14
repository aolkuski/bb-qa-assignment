package com.backbase.bblog.restapi;

import static com.backbase.bblog.uitesting.testutils.DateTimeUtils.getZonedDateTimeFromString;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.time.ZonedDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.backbase.bblog.baseclasses.BaseRestApiTest;
import com.backbase.bblog.dataobjects.Article;
import com.backbase.bblog.dataobjects.ArticleWrapper;


import io.restassured.http.ContentType;

public class ArticleCreationTests extends BaseRestApiTest {

    ArticleWrapper sourceArticleWrapped = ArticleWrapper.builder()
            .article(Article.builder()
                    .title("Article's Title RESTApi " + randomString)
                    .description("Article's Description RESTApi " + randomString)
                    .body("Article's Body RESTApi " + randomString)
                    .tagList(Arrays.asList("tagr1", "tagr2", "tagr3"))
                    .build()
            ).build();

    @Test
    @Tag("restapi")
    public void shouldBeAbleToCreateSimpleArticleTest() {
        Article createdArticle = given(getRequestSpecForAuthorizedCalls())
                .body(sourceArticleWrapped)
                .when().post("/articles")
                .then().extract().as(ArticleWrapper.class).getArticle();

        ZonedDateTime creationTime = ZonedDateTime.now();
        Article sourceArticle = sourceArticleWrapped.getArticle();
        assertThat(sourceArticle.getTitle()).isEqualTo(createdArticle.getTitle());
        assertThat(sourceArticle.getDescription()).isEqualTo(createdArticle.getDescription());
        assertThat(sourceArticle.getBody()).isEqualTo(createdArticle.getBody());
        assertThat(signedUpUser.getUsername()).isEqualTo(createdArticle.getAuthor().getUsername());
        assertThat(sourceArticle.getTagList()).isEqualTo(createdArticle.getTagList());
        assertThat(getZonedDateTimeFromString(createdArticle.getCreatedAt())).isBetween(creationTime.minusMinutes(2),
                creationTime.plusMinutes(2));
    }

    @Test
    @Tag("restapi")
    public void shouldNotBeAbleToCreateArticleWithoutAuthenticationTest() {
        ArticleWrapper changedArticle = ArticleWrapper.builder().article(
                sourceArticleWrapped.getArticle().toBuilder()
                        .title("Not authenticated title")
                        .build()
        ).build();

        String errorResponse = given()
                .contentType(ContentType.JSON)
                .body(changedArticle)
                .when().post("/articles")
                .then().assertThat()
                .statusCode(401)
                .extract().asString();

        assertThat(errorResponse).contains("No authorization token was found");
    }

}
