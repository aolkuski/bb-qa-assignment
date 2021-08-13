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

import com.codeborne.selenide.SelenideElement;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaitUtils {

    @SuppressWarnings("BusyWait")
    public static void waitForElementToStopMoving(SelenideElement element, long timeoutInMillis) {
        final long poolingIntervalInMillis = 500;
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
                sleep(poolingIntervalInMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            previousLocation = getLocation(element);
        }

    }

    private static Point getLocation(SelenideElement element) {
        Point elementLocation;
        try {
            elementLocation = element.getLocation();
        } catch (StaleElementReferenceException sere) {
            try {
                sleep(50);
            } catch (InterruptedException e) {
                //wait a moment to retry
            }
            elementLocation = element.getLocation();
        }
        return elementLocation;
    }

    public static void waitForCondition(Callable<Boolean> condition, long timeoutInMillis) {
        try {
            await()
                    .atMost(timeoutInMillis, MILLISECONDS)
                    .ignoreExceptions()
                    .pollInterval(10, MILLISECONDS)
                    .until(condition);
        } catch (ConditionTimeoutException e) {
            log.warn("Wait condition not met! " + condition.toString());
        }
    }
}
