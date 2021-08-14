package com.backbase.bblog.uitesting.pageobjects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

import com.backbase.bblog.uitesting.testutils.WaitUtils;
import com.codeborne.selenide.SelenideElement;


import io.qameta.allure.Step;

public class ProfileSettingsPage extends BasePage {

    private SelenideElement logoutButton = $x("//button[contains(.,'logout')]");

    @Override
    protected void waitForPageContent() {
        WaitUtils.waitForElementToStopMoving($x("//button[contains(.,'logout')]"));
    }

    @Step
    public HomePageUnauthenticated logout() {
        logoutButton.shouldBe(visible).click();
        return new HomePageUnauthenticated();
    }
}
