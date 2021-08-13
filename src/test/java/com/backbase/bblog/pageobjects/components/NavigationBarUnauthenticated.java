package com.backbase.bblog.pageobjects.components;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

import com.backbase.bblog.pageobjects.SignUpPage;
import com.codeborne.selenide.SelenideElement;

public class NavigationBarUnauthenticated {

    private SelenideElement signInLink = $x("//a[contains(@href, 'login')]");
    private SelenideElement signUpLink = $x("//a[contains(@href, 'register')]");

    public SignUpPage openSignUpPage() {
        signUpLink.shouldBe(visible).click();
        return new SignUpPage();
    }
}
