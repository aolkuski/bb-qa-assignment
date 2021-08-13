package com.backbase.bblog.pageobjects.components;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

public class NavigationBarAuthenticated {

    private SelenideElement userNameLink = $x("//a[contains(@href, '/profile/')]");
    private SelenideElement newArticleLink = $x("//a[contains(@href, '/editor')]");

    public String getUsernameLabel() {
        return userNameLink.shouldBe(visible).getText().trim();
    }

    public boolean isUserNameVisible() {
        return userNameLink.isDisplayed();
    }
}
