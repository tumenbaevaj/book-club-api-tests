package tests;

import models.login.LoginBodyModel;
import models.logout.LogoutBodyModel;
import models.registration.RegistrationBodyModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
