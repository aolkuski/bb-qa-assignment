package com.backbase.bblog.uitests.pageobjects;

public abstract class BasePage {

    protected BasePage() {
        waitForPageContent();
    }

    protected abstract void waitForPageContent();

}
