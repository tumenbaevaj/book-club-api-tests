package tests;

import models.login.LoginBodyModel;
import models.logout.BlankRefreshLogoutResponseModel;
import models.logout.LogoutBodyModel;
import models.registration.RegistrationBodyModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.TestData.LOGOUT_BLANK_REFRESH_ERROR;

public class LogoutTests extends TestBase {

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
    @DisplayName("Successful user logout")
    public void successfulLogoutTest() {

        LoginBodyModel loginData =
                new LoginBodyModel(username, password);

        String refreshToken =
                api.auth.loginAndGetRefreshToken(loginData);

        LogoutBodyModel logoutData =
                new LogoutBodyModel(refreshToken);

        api.auth.logout(logoutData);
    }

    @Test
    @DisplayName("Logout with blank refresh token")
    public void blankRefreshLogoutTest() {

        LogoutBodyModel logoutData =
                new LogoutBodyModel("");

        BlankRefreshLogoutResponseModel logoutResponse =
                api.auth.logoutBlankRefresh(logoutData);

        step("Check refresh token validation error", () -> {
            String actualError = logoutResponse.refresh().get(0);

            assertThat(actualError)
                    .isEqualTo(LOGOUT_BLANK_REFRESH_ERROR);
        });
    }
}