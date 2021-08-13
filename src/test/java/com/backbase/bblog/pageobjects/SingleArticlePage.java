package com.backbase.bblog.pageobjects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

import com.backbase.bblog.pageobjects.components.NavigationBarAuthenticated;
import com.backbase.bblog.testutils.WaitUtils;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class SingleArticlePage extends BasePage {

    private SelenideElement articleTitle = $x("//*[@class='article-page']//*[@class='container']/h1");
    private SelenideElement articlePublicationTimeLabel = $x("//app-article-meta//*[@class='date']");
    private SelenideElement articlePublisher = $x("//div[@class='info']/a");
    private SelenideElement articleBody = $x("//*[contains(@class,'article-content')]");
    private ElementsCollection articleTags = $$x("//*[contains(@class,'article-content')]//*[contains(@class,'tag-pill')]");

    private NavigationBarAuthenticated navigationBar;

    @Override
    protected void waitForPageContent() {
        WaitUtils.waitForCondition(() -> $x("//app-article-meta//*[@class='date']").isDisplayed(), 5000L);
    }

    public NavigationBarAuthenticated getNavigationBar() {
        if (navigationBar == null) {
            navigationBar = new NavigationBarAuthenticated();
        }
        return navigationBar;
    }

    public String getArticleTitle() {
        return articleTitle.shouldBe(visible).getText().trim();
    }

    public String getPublicationTimeLabel() {
        return articlePublicationTimeLabel.shouldBe(visible).getText().trim();
    }

    public String getPublisherUsername() {
        return articlePublisher.shouldBe(visible).getText().trim();
    }

    public String getArticleBody() {
        return articleBody.shouldBe(visible).getText().trim();
    }

    public String getArticleBodyInnerHtml() {
        return articleBody.shouldBe(visible).innerHtml().trim();
    }
}
