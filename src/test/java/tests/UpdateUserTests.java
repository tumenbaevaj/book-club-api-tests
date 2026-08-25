package tests;

import models.login.LoginBodyModel;
import models.login.SuccessfulLoginResponseModel;
import models.registration.RegistrationBodyModel;
import models.user.*;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.TestData.REQUIRED_FIELD_ERROR;

public class UpdateUserTests extends TestBase {

    Faker faker = new Faker();

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
    @DisplayName("Successful user update with PUT")
    public void successfulPutUpdateUserTest() {

        LoginBodyModel loginData =
                new LoginBodyModel(username, password);

        SuccessfulLoginResponseModel loginResponse =
                api.auth.login(loginData);

        String accessToken = loginResponse.access();

        String updatedUsername =
                "updated_user_" + System.currentTimeMillis();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();

        UpdateUserBodyModel updateData =
                new UpdateUserBodyModel(
                        updatedUsername,
                        firstName,
                        lastName,
                        email
                );

        UpdateUserResponseModel updateResponse =
                api.users.updateUserPut(updateData, accessToken);

        step("Check updated user data", () -> {
            assertThat(updateResponse.id()).isGreaterThan(0);
            assertThat(updateResponse.username()).isEqualTo(updatedUsername);
            assertThat(updateResponse.firstName()).isEqualTo(firstName);
            assertThat(updateResponse.lastName()).isEqualTo(lastName);
            assertThat(updateResponse.email()).isEqualTo(email);
            assertThat(updateResponse.remoteAddr()).isNotBlank();
        });
    }

    @Test
    @DisplayName("Successful user update with PATCH")
    public void successfulPatchUpdateUserTest() {

        LoginBodyModel loginData =
                new LoginBodyModel(username, password);

        SuccessfulLoginResponseModel loginResponse =
                api.auth.login(loginData);

        String accessToken = loginResponse.access();

        String firstName = faker.name().firstName();

        PatchUserBodyModel patchData =
                new PatchUserBodyModel(firstName);

        UpdateUserResponseModel updateResponse =
                api.users.updateUserPatch(patchData, accessToken);

        step("Check patched user data", () -> {
            assertThat(updateResponse.id()).isGreaterThan(0);
            assertThat(updateResponse.username()).isEqualTo(username);
            assertThat(updateResponse.firstName()).isEqualTo(firstName);
            assertThat(updateResponse.lastName()).isEqualTo("");
            assertThat(updateResponse.email()).isEqualTo("");
            assertThat(updateResponse.remoteAddr()).isNotBlank();
        });
    }

    @Test
    @DisplayName("User update with invalid PUT data")
    public void invalidPutUpdateUserTest() {

        LoginBodyModel loginData =
                new LoginBodyModel(username, password);

        SuccessfulLoginResponseModel loginResponse =
                api.auth.login(loginData);

        String accessToken = loginResponse.access();

        String updatedUsername =
                "updated_user_" + System.currentTimeMillis();

        InvalidPutUpdateUserBodyModel updateData =
                new InvalidPutUpdateUserBodyModel(updatedUsername);

        InvalidPutUpdateUserResponseModel updateResponse =
                api.users.updateUserPutInvalid(updateData, accessToken);

        step("Check validation errors", () -> {
            assertThat(updateResponse.firstName().get(0))
                    .isEqualTo(REQUIRED_FIELD_ERROR);

            assertThat(updateResponse.lastName().get(0))
                    .isEqualTo(REQUIRED_FIELD_ERROR);

            assertThat(updateResponse.email().get(0))
                    .isEqualTo(REQUIRED_FIELD_ERROR);
        });
    }

    @Test
    @DisplayName("Get current user")
    public void successfulGetCurrentUserTest() {

        LoginBodyModel loginData =
                new LoginBodyModel(username, password);

        SuccessfulLoginResponseModel loginResponse =
                api.auth.login(loginData);

        String accessToken = loginResponse.access();

        UpdateUserResponseModel userResponse =
                api.users.getCurrentUser(accessToken);

        step("Check current user data", () -> {
            assertThat(userResponse.id()).isGreaterThan(0);
            assertThat(userResponse.username()).isEqualTo(username);
        });
    }

    @Test
    @DisplayName("Delete current user")
    public void successfulDeleteCurrentUserTest() {

        LoginBodyModel loginData =
                new LoginBodyModel(username, password);

        SuccessfulLoginResponseModel loginResponse =
                api.auth.login(loginData);

        String accessToken = loginResponse.access();

        api.users.deleteCurrentUser(accessToken);
    }
}
