package com.backbase.bblog.baseclasses;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.codeborne.selenide.Configuration;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseTest {

    static final long PAGE_LOAD_TIMEOUT_MILLIS = 10000;
    static final String BASE_URL = "https://qa-task.backbasecloud.com/";
    static final String AUTH_USERNAME = "candidatex";
    static final String AUTH_PASS = "qa-is-cool";

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
    public void testSetup() {
        randomString = RandomStringUtils.randomAlphanumeric(6);
        log.info("Random string sequence for test: " + randomString);
    }
}
