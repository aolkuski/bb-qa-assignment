package com.backbase.bblog.uitests;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.backbase.bblog.baseclasses.BaseUiTest;
import com.backbase.bblog.dataobjects.Article;
import com.backbase.bblog.dataobjects.Author;
import com.backbase.bblog.pageobjects.HomePageAuthenticated;
import com.backbase.bblog.pageobjects.SingleArticlePage;
import com.backbase.bblog.testutils.DateTimeUtils;

public class ArticleCreationTests extends BaseUiTest {
    HomePageAuthenticated mainPage;
    String userName;

    @BeforeEach
    public void signUp() {
        userName = "user" + randomString;
        mainPage = homePage
                .openSignUpPage()
                .signUpSuccessfully(userName, "mail+" + randomString + "@mail.com", "password" + randomString);
    }

    @AfterEach
    public void goBackToMainPage() {
        mainPage.getNavigationBar()
                .openProfilePage()
                .openProfileSettingsPage()
                .logout();
    }

    @Test
    @Tag("ui")
    public void successfulSimpleArticleCreation() {
        Article simpleArticle = new Article()
                .withTitle("Article's Title " + randomString)
                .withDescription("Article's Description " + randomString)
                .withBody("Article's Body " + randomString)
                .withAuthor(new Author().withUsername(userName.toLowerCase()))
                .withCreatedAt(DateTimeUtils.getCurrentDateInSimpleFormat());

        SingleArticlePage singleArticlePage = mainPage
                .getNavigationBar()
                .openNewArticlePage()
                .fillTextFields(simpleArticle)
                .publishArticle();

        assertThat(singleArticlePage.getArticleTitle()).isEqualTo(simpleArticle.getTitle());
        assertThat(singleArticlePage.getArticleBody()).isEqualTo(simpleArticle.getBody());
        assertThat(singleArticlePage.getPublicationTimeLabel()).isEqualTo(simpleArticle.getCreatedAt());
        assertThat(singleArticlePage.getPublisherUsername()).isEqualTo(simpleArticle.getAuthor().getUsername());
    }

    @Test
    @Tag("ui")
    public void successfulFullArticleCreation() {
        Article article = new Article()
                .withTitle("Article's Title " + randomString)
                .withDescription("Article's Description " + randomString)
                .withBody("Article's Body " + randomString)
                .withTagList(Arrays.asList("tag1", "tag2", "tag3"))
                .withAuthor(new Author().withUsername(userName.toLowerCase()))
                .withCreatedAt(DateTimeUtils.getCurrentDateInSimpleFormat());

        Article mostRecentArticle = mainPage
                .getNavigationBar()
                .openNewArticlePage()
                .fillTextFields(article)
                .publishArticle()
                .getNavigationBar().openProfilePage()
                .getMostRecentArticle();

        assertThat(mostRecentArticle.getTitle()).isEqualTo(article.getTitle());
        assertThat(mostRecentArticle.getAuthor().getUsername()).isEqualTo(article.getAuthor().getUsername());
        assertThat(mostRecentArticle.getDescription()).isEqualTo(article.getDescription());
        assertThat(mostRecentArticle.getCreatedAt()).isEqualTo(article.getCreatedAt());
        assertThat(mostRecentArticle.getTagList().containsAll(article.getTagList())).as("Tag lists differs!").isTrue();

    }
}
