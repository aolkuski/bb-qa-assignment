package com.backbase.bblog.restapitests;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.backbase.bblog.baseclasses.BaseRestApiTest;
import com.backbase.bblog.dataobjects.User;
import com.backbase.bblog.dataobjects.UserWrapper;

public class RegisterUserTests extends BaseRestApiTest {

    @Test
    @Tag("restapi")
    public void shouldNotBeAbleToRegisterWithExistingEmailOrUsername() {
        UserWrapper userWithDuplicatedData = UserWrapper.builder()
                .user(User.builder()
                        .username(signedUpUser.getUsername())
                        .email(signedUpUser.getEmail())
                        .password("anyPassword")
                        .build())
                .build();

        String errorResponse = given()
                .contentType(JSON)
                .body(userWithDuplicatedData)
                .when().post("/users")
                .then()
                .assertThat()
                .statusCode(422)
                .extract().asString();

        assertThat(errorResponse).contains("\"username\":\"is already taken.\"");
        assertThat(errorResponse).contains("\"email\":\"is already taken.\"");
    }

}
