package com.backbase.bblog.baseclasses;

import org.junit.jupiter.api.BeforeEach;

import com.backbase.bblog.pageobjects.HomePageUnauthenticated;
import com.codeborne.selenide.Selenide;


import io.qameta.allure.Step;

public class BaseUiTest extends BaseTest {
    protected HomePageUnauthenticated homePage;

    @BeforeEach
    @Step
    public void openApplication() {
        Selenide.open(BASE_URL, "", AUTH_USERNAME, AUTH_PASS);
        homePage = new HomePageUnauthenticated();
    }
}
