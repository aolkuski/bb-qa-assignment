package com.backbase.bblog.uitesting.pageobjects.components;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

import com.backbase.bblog.uitesting.pageobjects.SignUpPage;
import com.codeborne.selenide.SelenideElement;


import io.qameta.allure.Step;

public class NavigationBarUnauthenticated {

    private SelenideElement signInLink = $x("//a[contains(@href, 'login')]");
    private SelenideElement signUpLink = $x("//a[contains(@href, 'register')]");

    @Step
    public SignUpPage openSignUpPage() {
        signUpLink.shouldBe(visible).click();
        return new SignUpPage();
    }
}
