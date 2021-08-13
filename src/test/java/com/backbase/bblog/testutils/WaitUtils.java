package com.backbase.bblog.testutils;

import static java.lang.Thread.sleep;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.awaitility.Awaitility.await;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.Callable;

import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaitUtils {

    public static void waitForElementToStopMoving(SelenideElement element) {
        waitForElementToStopMoving(element, Configuration.pageLoadTimeout);
    }

    @SuppressWarnings("BusyWait")
    public static void waitForElementToStopMoving(SelenideElement element, long timeoutInMillis) {
        final long POOLING_INTERVAL_LONG = 500L;
        final LocalTime startTime = LocalTime.now();
        Point previousLocation;
        try {
            previousLocation = getLocation(element);
        } catch (StaleElementReferenceException sere) {
            // retry
            log.warn("Couldn't obtain element's location due to StaleElementReferenceException. Retrying.");
            previousLocation = getLocation(element);
        }
        while (!getLocation(element).equals(previousLocation)) {
            if (Duration.between(startTime, LocalTime.now()).toMillis() > timeoutInMillis) {
                log.warn("Waiting for element to stop moving exceeded provided timeout.");
                break;
            }
            try {
                sleep(POOLING_INTERVAL_LONG);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            previousLocation = getLocation(element);
        }
    }

    private static Point getLocation(SelenideElement element) {
        final long POOLING_INTERVAL_SHORT = 50L;
        Point elementLocation;
        try {
            elementLocation = element.getLocation();
        } catch (StaleElementReferenceException sere) {
            try {
                sleep(POOLING_INTERVAL_SHORT);
            } catch (InterruptedException e) {
                //wait a moment to retry
            }
            elementLocation = element.getLocation();
        }
        return elementLocation;
    }

    public static void waitForCondition(Callable<Boolean> condition, long timeoutInMillis) {
        final long POOLING_INTERVAL_SHORT = 50L;
        WebDriver webDriver = WebDriverRunner.getWebDriver();
        try {
            await()
                    .atMost(timeoutInMillis, MILLISECONDS)
                    .ignoreExceptions()
                    .pollInterval(POOLING_INTERVAL_SHORT, MILLISECONDS)
                    .until(() -> {
                        WebDriverRunner.setWebDriver(webDriver);
                        return condition.call();
                    });
        } catch (ConditionTimeoutException e) {
            log.warn("Wait condition not met! " + condition.toString());
        }
    }
}
