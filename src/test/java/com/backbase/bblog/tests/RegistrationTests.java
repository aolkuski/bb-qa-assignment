package com.backbase.bblog.tests;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.backbase.bblog.baseclasses.BaseUiTest;
import com.backbase.bblog.pageobjects.HomePageAuthenticated;

public class RegistrationTests extends BaseUiTest {

    @Tag("ui")
    @Test
    public void successfulSignUpTest() {
        String userName = "user" + randomString;
        HomePageAuthenticated homePageAuthenticated = homePage
                .openSignUpPage()
                .signUpSuccessfully(userName, "mail+" + randomString + "@mail.com", "password" + randomString);

        assertThat(homePageAuthenticated.getNavigationBar().getUsernameLabel()).isEqualToIgnoringCase(userName);
    }
}
