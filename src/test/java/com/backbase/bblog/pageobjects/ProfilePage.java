package com.backbase.bblog.pageobjects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

import com.backbase.bblog.dataobjects.Article;
import com.backbase.bblog.dataobjects.Author;
import com.backbase.bblog.testutils.WaitUtils;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;


import io.qameta.allure.Step;

public class ProfilePage extends BasePage {

    private SelenideElement editProfileSettingsLinkButton = $x("//a[@href='/settings']");
    private SelenideElement latestArticlePane = $x("(//*[contains(@class,'article-preview')])[1]");
    private SelenideElement latestArticleTitle = latestArticlePane.$x(".//a[@class='preview-link']/h1");
    private SelenideElement latestArticleDescription = latestArticlePane.$x(".//a[@class='preview-link']/p");
    private SelenideElement latestArticlePublisher = latestArticlePane.$x(".//a[@class='author']");
    private SelenideElement latestArticleCreationDate = latestArticlePane.$x(".//*[@class='date']");
    private ElementsCollection latestArticleTags = latestArticlePane.$$x(".//a[@class='preview-link']//ul/li");

    @Override
    protected void waitForPageContent() {
        WaitUtils.waitForCondition(() -> $x("//a[contains(normalize-space(text()),'My Posts')]").isDisplayed(), 5000L);
    }

    @Step
    public ProfileSettingsPage openProfileSettingsPage() {
        editProfileSettingsLinkButton.shouldBe(visible).click();
        return new ProfileSettingsPage();
    }

    public Article getMostRecentArticle() {
        return new Article()
                .withAuthor(new Author().withUsername(latestArticlePublisher.shouldBe(visible).getText().trim()))
                .withCreatedAt(latestArticleCreationDate.getText().trim())
                .withTitle(latestArticleTitle.getText().trim())
                .withDescription(latestArticleDescription.getText().trim())
                .withTagList(latestArticleTags.texts());
    }
}
