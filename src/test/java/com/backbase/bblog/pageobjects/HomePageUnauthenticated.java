package com.backbase.bblog.pageobjects;

import static com.codeborne.selenide.Selenide.$x;

import com.backbase.bblog.pageobjects.components.NavigationBarUnauthenticated;
import com.backbase.bblog.testutils.WaitUtils;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;


import io.qameta.allure.Step;

public class HomePageUnauthenticated extends BasePage {

    private SelenideElement pageFooter = $x("//footer//a");

    private NavigationBarUnauthenticated navigationBar;

    @Override
    protected void waitForPageContent() {
        WaitUtils.waitForElementToStopMoving($x("//footer//a"), Configuration.pageLoadTimeout);
    }

    public NavigationBarUnauthenticated getNavigationBar() {
        if (navigationBar == null) {
            navigationBar = new NavigationBarUnauthenticated();
        }
        return navigationBar;
    }

    @Step
    public SignUpPage openSignUpPage() {
        getNavigationBar().openSignUpPage();
        return new SignUpPage();
    }
}
