package com.backbase.bblog.uitesting.pageobjects;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;


import io.qameta.allure.Step;

public class SignUpPage extends BasePage {

    private SelenideElement signUpButton = $x("//button[normalize-space(text())='Sign up']");
    private SelenideElement usernameInputField = $x("//input[@placeholder='Username']");
    private SelenideElement emailInputField = $x("//input[@placeholder='Email']");
    private SelenideElement passwordInputField = $x("//input[@placeholder='Password']");

    @Override
    protected void waitForPageContent() {
        $x("//button[normalize-space(text())='Sign up']").shouldBe(visible);
    }

    private void insertSignUpData(String username, String mail, String password) {
        usernameInputField.shouldBe(visible).sendKeys(username);
        emailInputField.sendKeys(mail);
        passwordInputField.sendKeys(password);
    }

    @Step
    public HomePageAuthenticated signUpSuccessfully(String username, String mail, String password) {
        insertSignUpData(username, mail, password);
        signUpButton.shouldNotHave(attribute("disabled")).click();
        return new HomePageAuthenticated();
    }
}
