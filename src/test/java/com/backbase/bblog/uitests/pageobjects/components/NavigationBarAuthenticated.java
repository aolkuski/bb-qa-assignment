package com.backbase.bblog.uitests.pageobjects.components;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

import com.backbase.bblog.uitests.pageobjects.NewArticlePage;
import com.backbase.bblog.uitests.pageobjects.ProfilePage;
import com.codeborne.selenide.SelenideElement;


import io.qameta.allure.Step;

public class NavigationBarAuthenticated {

    private SelenideElement userNameLink = $x("//a[contains(@href, '/profile/')]");
    private SelenideElement newArticleLink = $x("//a[contains(@href, '/editor')]");

    public String getUsernameLabel() {
        return userNameLink.shouldBe(visible).getText().trim();
    }

    public boolean isUserNameVisible() {
        return userNameLink.isDisplayed();
    }

    @Step
    public NewArticlePage openNewArticlePage() {
        newArticleLink.shouldBe(visible).click();
        return new NewArticlePage();
    }

    @Step
    public ProfilePage openProfilePage() {
        userNameLink.shouldBe(visible).click();
        return new ProfilePage();
    }
}
