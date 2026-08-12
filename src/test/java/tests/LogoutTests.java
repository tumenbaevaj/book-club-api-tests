package tests;

import models.login.LoginBodyModel;
import models.logout.BlankRefreshLogoutResponseModel;
import models.logout.LogoutBodyModel;
import models.registration.RegistrationBodyModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void successfulLogoutTest() {
        LoginBodyModel loginData = new LoginBodyModel(username, password);
        String refreshToken = api.auth.loginAndGetRefreshToken(loginData);

        LogoutBodyModel logoutData = new LogoutBodyModel(refreshToken);
        api.auth.logout(logoutData);
    }

    @Test
    public void blankRefreshLogoutTest() {
        LogoutBodyModel logoutData = new LogoutBodyModel("");

        BlankRefreshLogoutResponseModel logoutResponse =
                api.auth.logoutBlankRefresh(logoutData);

        String expectedError = LOGOUT_BLANK_REFRESH_ERROR;
        String actualError = logoutResponse.refresh().get(0);

        assertThat(actualError).isEqualTo(expectedError);
    }
}
