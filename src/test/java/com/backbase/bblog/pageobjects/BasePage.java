package com.backbase.bblog.pageobjects;

public abstract class BasePage {

    protected BasePage() {
        waitForPageContent();
    }

    protected abstract void waitForPageContent();

}
