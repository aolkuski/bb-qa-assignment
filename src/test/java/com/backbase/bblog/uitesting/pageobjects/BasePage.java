package com.backbase.bblog.uitesting.pageobjects;

public abstract class BasePage {

    protected BasePage() {
        waitForPageContent();
    }

    protected abstract void waitForPageContent();

}
