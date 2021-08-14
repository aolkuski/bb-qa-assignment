package com.backbase.bblog.baseclasses;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpHeaders.ACCEPT;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;

import com.backbase.bblog.dataobjects.User;
import com.backbase.bblog.dataobjects.UserWrapper;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseRestApiTest extends BaseTest {

    private static final String JWT_AUTH = "jwtauthorization";
    private static final String JWT_TOKEN = "Token ";
    protected static User signedUpUser;
    protected String randomString;

    @BeforeAll
    public static void setupEngine() {
        RestAssured.authentication = RestAssured.basic(AUTH_USERNAME, AUTH_PASS);
        RestAssured.baseURI = BASE_URL + "api";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        String randomSequence = RandomStringUtils.randomAlphanumeric(6);
        UserWrapper bblogger =
                UserWrapper.builder().
                        user(User.builder()
                                .username("bbloggerApi" + randomSequence)
                                .password("bbloggerPass" + randomSequence)
                                .email("bblogger" + randomSequence + "@mail.com")
                                .build()
                        )
                        .build();

        signedUpUser = given()
                .contentType("application/json")
                .body(bblogger)
                .when().post("/users")
                .then().extract().as(UserWrapper.class).getUser();
        log.info("User created for this test run: " + signedUpUser);

    }

    public void setupRandomStringForTest() {
        randomString = RandomStringUtils.randomAlphanumeric(6);
        log.info("Random string sequence for run: " + randomString);
    }

    public RequestSpecification getRequestSpecForAuthorizedCalls() {
        return getRequestSpecForAuthorizedCalls(signedUpUser.getToken());
    }

    public RequestSpecification getRequestSpecForAuthorizedCalls(String jwtAuthorization) {
        String jwtAuthString;
        if (!jwtAuthorization.startsWith("Token")) {
            jwtAuthString = JWT_TOKEN + jwtAuthorization;
        } else {
            jwtAuthString = jwtAuthorization;
        }

        return new RequestSpecBuilder().addRequestSpecification(given())
                .setContentType(JSON)
                .addHeader(JWT_AUTH, jwtAuthString)
                .addHeader(ACCEPT, JSON.getAcceptHeader())
                .build();
    }

}
