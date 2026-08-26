package tests;

import models.registration.BlankUsernameRegistrationResponseModel;
import models.registration.ExistingUserResponseModel;
import models.registration.RegistrationBodyModel;
import models.registration.SuccessfulRegistrationResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.TestData.*;

public class RegistrationTests extends TestBase {

    String username;
    String password;

    @BeforeEach
    public void prepareTestData() {
        username = "user_" + System.currentTimeMillis();
        password = "pass_" + System.currentTimeMillis();
    }

    @Test
    @DisplayName("Successful user registration")
    public void successfulRegistrationTest() {

        RegistrationBodyModel registrationData =
                new RegistrationBodyModel(username, password);

        SuccessfulRegistrationResponseModel registrationResponse =
                api.users.register(registrationData);

        step("Check registration response", () -> {
            assertThat(registrationResponse.id()).isGreaterThan(0);
            assertThat(registrationResponse.username()).isEqualTo(username);
            assertThat(registrationResponse.firstName()).isEqualTo("");
            assertThat(registrationResponse.lastName()).isEqualTo("");
            assertThat(registrationResponse.email()).isEqualTo("");
            assertThat(registrationResponse.remoteAddr())
                    .matches(REGISTRATION_IP_REGEXP);
        });
    }

    @Test
    @DisplayName("Registration of an existing user")
    public void existingUserWrongRegistrationTest() {

        RegistrationBodyModel registrationData =
                new RegistrationBodyModel(username, password);

        SuccessfulRegistrationResponseModel firstRegistrationResponse =
                api.users.register(registrationData);

        step("Check first registration", () ->
                assertThat(firstRegistrationResponse.username())
                        .isEqualTo(username));

        ExistingUserResponseModel secondRegistrationResponse =
                api.users.registerExistingUser(registrationData);

        step("Check existing user error", () ->
                assertThat(secondRegistrationResponse.username().get(0))
                        .isEqualTo(REGISTRATION_EXISTING_USER_ERROR));
    }

    @Test
    @DisplayName("Registration with blank username")
    public void blankUsernameRegistrationTest() {

        RegistrationBodyModel registrationData =
                new RegistrationBodyModel("", password);

        BlankUsernameRegistrationResponseModel registrationResponse =
                api.users.registerBlankUsername(registrationData);

        step("Check username validation error", () ->
                assertThat(registrationResponse.username().get(0))
                        .isEqualTo(REGISTRATION_BLANK_USERNAME_ERROR));
    }
}
