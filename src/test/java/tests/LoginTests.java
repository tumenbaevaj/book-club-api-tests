package tests;

import models.login.BlankPasswordLoginResponseModel;
import models.login.LoginBodyModel;
import models.login.SuccessfulLoginResponseModel;
import models.login.WrongCredentialsLoginResponseModel;
import models.registration.RegistrationBodyModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.TestData.*;

public class LoginTests extends TestBase {

    String username;
    String password;

    @BeforeEach
    public void prepareTestData() {
        username = "user_" + System.currentTimeMillis();
        password = "pass_" + System.currentTimeMillis();

        RegistrationBodyModel registrationData =
                new RegistrationBodyModel(username, password);

        api.users.register(registrationData);
    }

    @Test
    @DisplayName("Successful user login")
    public void successfulLoginTest() {

        LoginBodyModel loginData =
                new LoginBodyModel(username, password);

        SuccessfulLoginResponseModel loginResponse =
                api.auth.login(loginData);

        step("Check login response", () -> {
            String actualAccess = loginResponse.access();
            String actualRefresh = loginResponse.refresh();

            assertThat(actualAccess)
                    .startsWith(LOGIN_TOKEN_PREFIX);

            assertThat(actualRefresh)
                    .startsWith(LOGIN_TOKEN_PREFIX);

            assertThat(actualAccess)
                    .isNotEqualTo(actualRefresh);
        });
    }

    @Test
    @DisplayName("Login with wrong credentials")
    public void wrongCredentialsLoginTest() {

        LoginBodyModel loginData =
                new LoginBodyModel(username, LOGIN_WRONG_PASSWORD);

        WrongCredentialsLoginResponseModel loginResponse =
                api.auth.loginWrongCredentials(loginData);

        step("Check error message", () ->
                assertThat(loginResponse.detail())
                        .isEqualTo(LOGIN_WRONG_CREDENTIALS_ERROR));
    }

    @Test
    @DisplayName("Login with blank password")
    public void blankPasswordLoginTest() {

        LoginBodyModel loginData =
                new LoginBodyModel(username, "");

        BlankPasswordLoginResponseModel loginResponse =
                api.auth.loginBlankPassword(loginData);

        step("Check password validation error", () ->
                assertThat(loginResponse.password().get(0))
                        .isEqualTo(LOGIN_BLANK_PASSWORD_ERROR));
    }
}
