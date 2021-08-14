package com.backbase.bblog.baseclasses;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseTest {
    static final long PAGE_LOAD_TIMEOUT_MILLIS = 10000;
    static final String BASE_URL = "https://qa-task.backbasecloud.com/";
    static final String AUTH_USERNAME = "candidatex";
    static final String AUTH_PASS = "qa-is-cool";

}
