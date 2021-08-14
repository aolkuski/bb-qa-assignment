package com.backbase.bblog.baseclasses;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.backbase.bblog.uitesting.pageobjects.HomePageUnauthenticated;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseUiTest extends BaseTest {
    protected HomePageUnauthenticated homePage;
    protected String randomString;

    @BeforeAll
    public static void setup() {
        Configuration.baseUrl = BASE_URL;
        Configuration.pageLoadTimeout = PAGE_LOAD_TIMEOUT_MILLIS;
        Configuration.reopenBrowserOnFail = true;
        Configuration.screenshots = true;
        Configuration.startMaximized = false;
    }

    @BeforeEach
    public void openApplication() {
        randomString = RandomStringUtils.randomAlphanumeric(6);
        log.info("Random string sequence for test run: " + randomString);
        Selenide.open(BASE_URL, "", AUTH_USERNAME, AUTH_PASS);
        homePage = new HomePageUnauthenticated();
    }
}
