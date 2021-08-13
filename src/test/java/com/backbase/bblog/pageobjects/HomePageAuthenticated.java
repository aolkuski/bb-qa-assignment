package com.backbase.bblog.pageobjects;

import static com.codeborne.selenide.Selenide.$x;

import com.backbase.bblog.pageobjects.components.NavigationBarAuthenticated;
import com.backbase.bblog.testutils.WaitUtils;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

public class HomePageAuthenticated extends BasePage {

    private SelenideElement pageFooter = $x("//footer//a");
    private SelenideElement signUpLink = $x("//a[normalize-space(text())='Sign up']");

    private NavigationBarAuthenticated navigationBar;

    @Override
    protected void waitForPageContent() {
        WaitUtils.waitForElementToStopMoving($x("//footer//a"), Configuration.pageLoadTimeout);
        WaitUtils.waitForCondition(() -> $x("//a[contains(text(), 'Your Feed') and contains(@class, 'active')]").exists(), 10000L);
    }

    public NavigationBarAuthenticated getNavigationBar() {
        if (navigationBar == null) {
            navigationBar = new NavigationBarAuthenticated();
        }
        return navigationBar;
    }

}
