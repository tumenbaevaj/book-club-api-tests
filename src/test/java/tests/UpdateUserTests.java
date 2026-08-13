package tests;

import models.login.LoginBodyModel;
import models.login.SuccessfulLoginResponseModel;
import models.registration.RegistrationBodyModel;
import models.user.*;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdateUserTests extends TestBase {

    Faker faker = new Faker();

    String username;
    String password;

    @BeforeEach
    public void prepareTestData() {
        username = "user_" + System.currentTimeMillis();
        password = "pass_" + System.currentTimeMillis();

        RegistrationBodyModel registrationData = new RegistrationBodyModel(username, password);

        api.users.register(registrationData);
    }

    @Test
    public void successfulPutUpdateUserTest() {
        LoginBodyModel loginData = new LoginBodyModel(username, password);

        SuccessfulLoginResponseModel loginResponse = api.auth.login(loginData);

        String accessToken = loginResponse.access();

        String updatedUsername = "updated_user_" + System.currentTimeMillis();

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

        assertThat(updateResponse.id()).isGreaterThan(0);
        assertThat(updateResponse.username()).isEqualTo(updatedUsername);
        assertThat(updateResponse.firstName()).isEqualTo(firstName);
        assertThat(updateResponse.lastName()).isEqualTo(lastName);
        assertThat(updateResponse.email()).isEqualTo(email);
        assertThat(updateResponse.remoteAddr()).isNotBlank();
    }

    @Test
    public void successfulPatchUpdateUserTest() {
        LoginBodyModel loginData =
                new LoginBodyModel(username, password);

        SuccessfulLoginResponseModel loginResponse =
                api.auth.login(loginData);

        String accessToken = loginResponse.access();

        String firstName = faker.name().firstName();

        PatchUserBodyModel patchData = new PatchUserBodyModel(firstName);

        UpdateUserResponseModel updateResponse =
                api.users.updateUserPatch(patchData, accessToken);

        assertThat(updateResponse.id()).isGreaterThan(0);
        assertThat(updateResponse.username()).isEqualTo(username);
        assertThat(updateResponse.firstName()).isEqualTo(firstName);
        assertThat(updateResponse.lastName()).isEqualTo("");
        assertThat(updateResponse.email()).isEqualTo("");
        assertThat(updateResponse.remoteAddr()).isNotBlank();
    }

    @Test
    public void invalidPutUpdateUserTest() {
        LoginBodyModel loginData = new LoginBodyModel(username, password);

        SuccessfulLoginResponseModel loginResponse = api.auth.login(loginData);

        String accessToken = loginResponse.access();

        String updatedUsername = "updated_user_" + System.currentTimeMillis();

        InvalidPutUpdateUserBodyModel updateData = new InvalidPutUpdateUserBodyModel(updatedUsername);

        InvalidPutUpdateUserResponseModel updateResponse = api.users.updateUserPutInvalid(updateData, accessToken);

        assertThat(updateResponse.firstName().get(0))
                .isEqualTo("This field is required.");

        assertThat(updateResponse.lastName().get(0))
                .isEqualTo("This field is required.");

        assertThat(updateResponse.email().get(0))
                .isEqualTo("This field is required.");
    }
}
