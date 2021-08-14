package com.backbase.bblog.uitesting.pageobjects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

import com.backbase.bblog.dataobjects.Article;
import com.backbase.bblog.uitesting.testutils.WaitUtils;
import com.codeborne.selenide.SelenideElement;


import io.qameta.allure.Step;

public class NewArticlePage extends BasePage {

    private SelenideElement titleInputField = $x("//input[@formcontrolname='title']");
    private SelenideElement descriptionInputField = $x("//input[@formcontrolname='description']");
    private SelenideElement bodyInputField = $x("//textarea[@formcontrolname='body']");
    private SelenideElement tagsInputField = $x("//input[@placeholder='Enter tags']");
    private SelenideElement publishArticleButton = $x("//button[normalize-space(text())='Publish Article']");

    @Override
    protected void waitForPageContent() {
        WaitUtils.waitForCondition(() -> $x("//button[normalize-space(text())='Publish Article']").isDisplayed(), 5000L);
    }

    @Step
    public NewArticlePage fillTextFields(String title, String description, String body, String tags) {
        if (title != null) {
            titleInputField.sendKeys(title);
        }
        if (description != null) {
            descriptionInputField.sendKeys(description);
        }
        if (body != null) {
            bodyInputField.sendKeys(body);
        }
        if (tags != null) {
            tagsInputField.sendKeys(tags);
        }
        return this;
    }

    @Step
    public NewArticlePage fillTextFields(Article article) {
        return fillTextFields(article.getTitle(), article.getDescription(), article.getBody(),
                article.getTagList() != null ? String.join(" ", article.getTagList()) : null);
    }

    @Step
    public SingleArticlePage publishArticle() {
        publishArticleButton.shouldBe(visible).click();
        return new SingleArticlePage();
    }
}
